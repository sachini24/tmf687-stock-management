package com.example.TMF687.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "spec_id")),
            @AttributeOverride(name = "name", column = @Column(name = "spec_name")),
            @AttributeOverride(name = "version", column = @Column(name = "spec_version")),
            @AttributeOverride(name = "type", column = @Column(name = "spec_type"))  // <-- avoids conflict with Product.type
    })
    private ProductSpecification productSpecification;


    @ElementCollection
    @CollectionTable(name = "product_characteristics", joinColumns = @JoinColumn(name = "product_id"))
    private List<ProductCharacteristic> productCharacteristic;

    private String type;

    public Product() {}

    public Product(Long id, List<ProductCharacteristic> productCharacteristic,
                   ProductSpecification productSpecification, String type) {
        this.id = id;
        this.productCharacteristic = productCharacteristic;
        this.productSpecification = productSpecification;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductCharacteristic> getProductCharacteristic() {
        return productCharacteristic;
    }

    public void setProductCharacteristic(List<ProductCharacteristic> productCharacteristic) {
        this.productCharacteristic = productCharacteristic;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
