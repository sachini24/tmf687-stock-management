package com.example.TMF687.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stocked_product")
public class StockedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<ProductCharacteristic> productCharacteristic;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "spec_id")),
            @AttributeOverride(name = "name", column = @Column(name = "spec_name")),
            @AttributeOverride(name = "version", column = @Column(name = "spec_version")),
            @AttributeOverride(name = "type", column = @Column(name = "spec_type"))
    })
    private ProductSpecification productSpecification;


    private String type;

    // No-argument constructor
    public StockedProduct() {
    }

    // All-arguments constructor
    public StockedProduct(Long id, List<ProductCharacteristic> productCharacteristic,
                          ProductSpecification productSpecification, String type) {
        this.id = id;
        this.productCharacteristic = productCharacteristic;
        this.productSpecification = productSpecification;
        this.type = type;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public List<ProductCharacteristic> getProductCharacteristic() {
        return productCharacteristic;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setProductCharacteristic(List<ProductCharacteristic> productCharacteristic) {
        this.productCharacteristic = productCharacteristic;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public void setType(String type) {
        this.type = type;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockedProduct)) return false;
        StockedProduct that = (StockedProduct) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productCharacteristic, that.productCharacteristic) &&
                Objects.equals(productSpecification, that.productSpecification) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCharacteristic, productSpecification, type);
    }

    // toString
    @Override
    public String toString() {
        return "StockedProduct{" +
                "id=" + id +
                ", productCharacteristic=" + productCharacteristic +
                ", productSpecification=" + productSpecification +
                ", type='" + type + '\'' +
                '}';
    }
    @OneToOne(mappedBy = "stockedProduct")
    @JsonBackReference
    private ProductStock productStock;

}
