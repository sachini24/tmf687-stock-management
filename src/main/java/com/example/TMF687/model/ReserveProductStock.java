package com.example.TMF687.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
public class ReserveProductStock {

    @Id
    private String id;

    private Instant creationDate;

    @Column(nullable = false)
    private String state = "accepted"; // Default state

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "reserveProductStock")
    private List<ReserveProductStockItem> reserveProductStockItem;

    private String type;

    public ReserveProductStock() {
        this.id = UUID.randomUUID().toString();
    }


    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<ReserveProductStockItem> getReserveProductStockItem() {
        return reserveProductStockItem;
    }

    public void setReserveProductStockItem(List<ReserveProductStockItem> reserveProductStockItem) {
        this.reserveProductStockItem = reserveProductStockItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public String getHref() {
        return "http://localhost:8080/tmf-api/stock/v4/reserveProductStock/" + id;
    }
}
