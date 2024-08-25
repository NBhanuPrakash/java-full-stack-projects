package com.BhanuTeachie.Service;

import com.BhanuTeachie.Entity.Product;
import com.BhanuTeachie.Repo.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
class ProductQueryServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductQueryService productQueryService;


    @Test
    void getList() throws Exception {
        List<Product> list = Stream.of(new Product("jdkc", "Bhanu", "duhukjdlo", 9898.00), new Product("jdkc", "Bhanu", "duhukjdlo", 9898.00)).collect(Collectors.toList());
        Mockito.when(productRepository.findAll()).thenReturn(list);
        List<Product> products = productQueryService.listOfProducts();
        Assertions.assertEquals(list, products);
    }


}