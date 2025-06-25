package com.example.TMF687.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_stock")
public class ProductStock {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String productStockStatusType;

    @Embedded
    private Quantity productStockLevel;

    private String productStockUsageType;
    private String description;
    private String name;
    private Instant replenishmentDate;
    private String stockLevelCategory;
    private Instant creationDate;

    @JsonProperty("@type")
    private String type;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "max_amount")),
            @AttributeOverride(name = "units", column = @Column(name = "max_units"))
    })
    private Quantity maxStockLevel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "min_amount")),
            @AttributeOverride(name = "units", column = @Column(name = "min_units"))
    })
    private Quantity minStockLevel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "stock_alert_amount")),
            @AttributeOverride(name = "units", column = @Column(name = "stock_alert_units"))
    })
    private Quantity stockLevelAlert;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "reorder_amount")),
            @AttributeOverride(name = "units", column = @Column(name = "reorder_units"))
    })
    private Quantity reorderQuantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private StockedProduct stockedProduct;

    @OneToMany(mappedBy = "productStockTarget", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AdjustProductStockItem> adjustProductStockItems;

    public ProductStock() {}

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductStockStatusType() {
        return productStockStatusType;
    }

    public void setProductStockStatusType(String productStockStatusType) {
        this.productStockStatusType = productStockStatusType;
    }

    public Quantity getProductStockLevel() {
        return productStockLevel;
    }

    public void setProductStockLevel(Quantity productStockLevel) {
        this.productStockLevel = productStockLevel;
    }

    public String getProductStockUsageType() {
        return productStockUsageType;
    }

    public void setProductStockUsageType(String productStockUsageType) {
        this.productStockUsageType = productStockUsageType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getReplenishmentDate() {
        return replenishmentDate;
    }

    public void setReplenishmentDate(Instant replenishmentDate) {
        this.replenishmentDate = replenishmentDate;
    }

    public String getStockLevelCategory() {
        return stockLevelCategory;
    }

    public void setStockLevelCategory(String stockLevelCategory) {
        this.stockLevelCategory = stockLevelCategory;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Quantity getMaxStockLevel() {
        return maxStockLevel;
    }

    public void setMaxStockLevel(Quantity maxStockLevel) {
        this.maxStockLevel = maxStockLevel;
    }

    public Quantity getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(Quantity minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public Quantity getStockLevelAlert() {
        return stockLevelAlert;
    }

    public void setStockLevelAlert(Quantity stockLevelAlert) {
        this.stockLevelAlert = stockLevelAlert;
    }

    public Quantity getReorderQuantity() {
        return reorderQuantity;
    }

    public void setReorderQuantity(Quantity reorderQuantity) {
        this.reorderQuantity = reorderQuantity;
    }

    public StockedProduct getStockedProduct() {
        return stockedProduct;
    }

    public void setStockedProduct(StockedProduct stockedProduct) {
        this.stockedProduct = stockedProduct;
    }

    public List<AdjustProductStockItem> getAdjustProductStockItems() {
        return adjustProductStockItems;
    }

    public void setAdjustProductStockItems(List<AdjustProductStockItem> adjustProductStockItems) {
        this.adjustProductStockItems = adjustProductStockItems;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getHref() {
        return "http://localhost:8080/tmf-api/stock/v4/productStock/" + id;
    }

    @JsonProperty("id")
    public String getIdAsString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStock that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductStock{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
