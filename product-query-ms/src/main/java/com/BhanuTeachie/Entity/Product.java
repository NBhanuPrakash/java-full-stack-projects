package com.BhanuTeachie.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_QUERY")
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private double price;
}
