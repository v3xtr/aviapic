package com.api.backend.internal.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "product_pictures", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "picture_url", nullable = false)
    private List<String> pictureUrls = new ArrayList<>();

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

}
