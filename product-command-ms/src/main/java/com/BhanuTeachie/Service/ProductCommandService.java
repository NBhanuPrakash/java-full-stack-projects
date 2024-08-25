package com.BhanuTeachie.Service;


import com.BhanuTeachie.Dto.ProductEvent;
import com.BhanuTeachie.Entity.Product;
import com.BhanuTeachie.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Product createProduct(Product product) throws Exception {
        Product productDo = null;
        try {
            productDo = productRepository.save(product);
            ProductEvent event = new ProductEvent("CreateProduct", productDo);
            kafkaTemplate.send("product-event-topic", event);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return productDo;
    }

    public Product updateProduct(String id, Product product) throws Exception {
        Product product1 = productRepository.findById(id).get();
        Product productDo = null;
        try {
            if (product1 != null) {
                product1.setDescription(product.getDescription());
                product1.setName(product.getName());
                product1.setPrice(product.getPrice());
                productDo = productRepository.save(product1);
                ProductEvent event = new ProductEvent("UpdateProduct", productDo);
                kafkaTemplate.send("product-event-topic", event);
            } else {
                throw new Exception("Id not Exit..." + id);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return productDo;
    }
}
