package com.example.TMF687.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
public class AdjustProductStock {

    @Id
    private String id = UUID.randomUUID().toString();

    private String adjustReason;
    private String description;
    private Boolean instantSyncAdjust;

    private Instant requestedAdjustProductStockDate;
    private Instant completedAdjustProductStockDate;
    private Instant creationDate = Instant.now();
    private String state;
    private String type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adjust_product_stock_id")
    private List<AdjustProductStockItem> adjustProductStockItem;

    public AdjustProductStock() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAdjustReason() { return adjustReason; }
    public void setAdjustReason(String adjustReason) { this.adjustReason = adjustReason; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getInstantSyncAdjust() { return instantSyncAdjust; }
    public void setInstantSyncAdjust(Boolean instantSyncAdjust) { this.instantSyncAdjust = instantSyncAdjust; }

    public Instant getRequestedAdjustProductStockDate() { return requestedAdjustProductStockDate; }
    public void setRequestedAdjustProductStockDate(Instant requestedAdjustProductStockDate) {
        this.requestedAdjustProductStockDate = requestedAdjustProductStockDate;
    }

    public Instant getCompletedAdjustProductStockDate() { return completedAdjustProductStockDate; }
    public void setCompletedAdjustProductStockDate(Instant completedAdjustProductStockDate) {
        this.completedAdjustProductStockDate = completedAdjustProductStockDate;
    }

    public Instant getCreationDate() { return creationDate; }
    public void setCreationDate(Instant creationDate) { this.creationDate = creationDate; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public List<AdjustProductStockItem> getAdjustProductStockItem() { return adjustProductStockItem; }
    public void setAdjustProductStockItem(List<AdjustProductStockItem> adjustProductStockItem) {
        this.adjustProductStockItem = adjustProductStockItem;
    }

    @Transient
    public String getHref() {
        return "http://localhost:8080/tmf-api/stock/v4/adjustProductStock/" + id;
    }
}
