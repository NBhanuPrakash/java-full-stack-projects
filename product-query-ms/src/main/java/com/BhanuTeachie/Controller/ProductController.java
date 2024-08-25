package com.BhanuTeachie.Controller;


import com.BhanuTeachie.Entity.Product;
import com.BhanuTeachie.Service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/getProducts")
public class ProductController {

    @Autowired
    private ProductQueryService productQueryService;

    @GetMapping
    public List<Product> listOfProducts() throws Exception {
        return productQueryService.listOfProducts();
    }
}
