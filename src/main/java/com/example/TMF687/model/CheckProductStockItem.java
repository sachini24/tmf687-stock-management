package com.example.TMF687.model;

import jakarta.persistence.*;

@Entity
public class CheckProductStockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String availabilityResult;

    // Add other fields as needed...

    private String type;

    public CheckProductStockItem() {
    }

    public CheckProductStockItem(Long id, String availabilityResult, String type) {
        this.id = id;
        this.availabilityResult = availabilityResult;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvailabilityResult() {
        return availabilityResult;
    }

    public void setAvailabilityResult(String availabilityResult) {
        this.availabilityResult = availabilityResult;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
