package com.BhanuTeachie.Controller;

import com.BhanuTeachie.Entity.Product;
import com.BhanuTeachie.Service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductCommandService productCommandService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) throws Exception {
        return productCommandService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) throws Exception {
        return productCommandService.updateProduct(id, product);
    }
}
