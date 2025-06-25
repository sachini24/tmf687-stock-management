package com.example.TMF687.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
public class CheckProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant creationDate;

    private String state; // e.g. done, pending, etc.

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "check_product_stock_id")
    private List<CheckProductStockItem> checkProductStockItem;

    private String type;

    public CheckProductStock() {
    }

    public CheckProductStock(Long id, Instant creationDate, String state, List<CheckProductStockItem> checkProductStockItem, String type) {
        this.id = id;
        this.creationDate = creationDate;
        this.state = state;
        this.checkProductStockItem = checkProductStockItem;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<CheckProductStockItem> getCheckProductStockItem() {
        return checkProductStockItem;
    }

    public void setCheckProductStockItem(List<CheckProductStockItem> checkProductStockItem) {
        this.checkProductStockItem = checkProductStockItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public String getHref() {
        return "http://localhost:8080/tmf-api/stock/v4/checkProductStock/" + id;
    }
}
