package com.BhanuTeachie.Dto;


import com.BhanuTeachie.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {

    private String eventType;

    private Product product;
}
