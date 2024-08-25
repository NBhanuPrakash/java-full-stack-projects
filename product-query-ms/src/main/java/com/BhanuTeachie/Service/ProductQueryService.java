package com.BhanuTeachie.Service;


import com.BhanuTeachie.Dto.ProductEvent;
import com.BhanuTeachie.Entity.Product;
import com.BhanuTeachie.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public List<Product> listOfProducts() throws Exception {
        try {
            return productRepository.findAll();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvent(ProductEvent productEvent) {
        if (productEvent.getEventType().equals("CreateProduct")) {
            productRepository.save(productEvent.getProduct());
        }
        if (productEvent.getEventType().equals("UpdateProduct")) {
            Product product = productRepository.findById(productEvent.getProduct().getId()).get();
            if (product != null) {
                product.setDescription(productEvent.getProduct().getDescription());
                product.setName(productEvent.getProduct().getName());
                product.setPrice(productEvent.getProduct().getPrice());
                productRepository.save(product);

            }
        }
    }
}
