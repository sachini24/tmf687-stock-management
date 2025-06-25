package com.example.TMF687.model;

import jakarta.persistence.*;

@Entity
public class CheckedProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private StockedProduct stockedProduct;

    private String type;

    public CheckedProductStock() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockedProduct getStockedProduct() {
        return stockedProduct;
    }

    public void setStockedProduct(StockedProduct stockedProduct) {
        this.stockedProduct = stockedProduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}