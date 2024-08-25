package com.BhanuTeachie.Controller;

import com.BhanuTeachie.Entity.Product;
import com.BhanuTeachie.Service.ProductQueryService;
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
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductQueryService productQueryService;

    @Test
    void getlist() throws Exception {
        List<Product> list = Stream.of(new Product("jdkc", "Bhanu", "duhukjdlo", 9898.00), new Product("jdkc", "Bhanu", "duhukjdlo", 9898.00)).collect(Collectors.toList());
        Mockito.when(productQueryService.listOfProducts()).thenReturn(list);

        List<Product> products = productController.listOfProducts();
        Assertions.assertEquals(list.size(), products.size());

    }

}