package com.BhanuTeachie.Controller;

import com.BhanuTeachie.Entity.Product;
import com.BhanuTeachie.Service.ProductCommandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductCommandService productCommandService;

    @InjectMocks
    private ProductController productController;


    @Test
    void createProduct() throws Exception {
        Product product = new Product("dgvhjkslds", "Bhanu", "dkbkjedls", 9999.90);
        Mockito.when(productCommandService.createProduct(product)).thenReturn(product);
        Product product1 = productController.createProduct(product);
        Assertions.assertEquals(product, product1);
    }

    @Test
    void updateProduct() throws Exception {
        Product product = new Product("dgvhjkslds", "Bhanu", "dkbkjedls", 9999.90);
        Mockito.when(productCommandService.updateProduct("dgvhjkslds", product)).thenReturn(product);
        productController.updateProduct("dgvhjkslds", product);
    }
}