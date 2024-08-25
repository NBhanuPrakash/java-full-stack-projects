package com.BhanuTeachie.Service;


import com.BhanuTeachie.Dto.ProductEvent;
import com.BhanuTeachie.Entity.Product;
import com.BhanuTeachie.Repo.ProductRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ProductCommandServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductCommandService productCommandService;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;


    @Test
    void createProduct() throws Exception {
        Product product = new Product("dgvhjkslds", "Bhanu", "dkbkjedls", 9999.90);
        ProductEvent productEvent = new ProductEvent("CreateProduct", product);
        when(productRepository.save(product)).thenReturn(product);
        Product product1 = productCommandService.createProduct(product);
        verify(kafkaTemplate, Mockito.times(1)).send("product-event-topic", productEvent);
        assertEquals(product, product1);
    }

    @Test
    void updateProduct() throws Exception {
        String id = "1";
        Product existingProduct = new Product(id, "Existing Product", "Existing Description", 200.0);
        Product updatedProduct = new Product(id, "Updated Product", "Updated Description", 300.0);
        ProductEvent expectedEvent = new ProductEvent("UpdateProduct", updatedProduct);

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        Product result = productCommandService.updateProduct(id, updatedProduct);

        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(existingProduct);
        verify(kafkaTemplate, times(1)).send("product-event-topic", expectedEvent);
        assertEquals(updatedProduct, result);
    }

    @Test
    public void testCreateProduct_IdNotFound() {
        when(productRepository.save(any())).thenThrow(new RuntimeException("Server issue.."));

        Exception exception = assertThrows(Exception.class, () -> {
            productCommandService.createProduct(any());
        });

        assertEquals("Server issue..", exception.getMessage());
        verify(kafkaTemplate, never()).send(any(), any());

    }


}
