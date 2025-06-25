package com.example.TMF687.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class ReserveProductStockItem {

    @Id
    private String id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "quantity_reserved_amount")),
            @AttributeOverride(name = "units", column = @Column(name = "quantity_reserved_units"))
    })
    private Quantity quantityReserved;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "quantity_requested_amount")),
            @AttributeOverride(name = "units", column = @Column(name = "quantity_requested_units"))
    })
    private Quantity quantityRequested;

    @ElementCollection
    @CollectionTable(name = "reserve_product_characteristics", joinColumns = @JoinColumn(name = "reserve_item_id"))
    private List<ProductCharacteristic> productCharacteristic;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "specificationId", column = @Column(name = "spec_id")),
            @AttributeOverride(name = "name", column = @Column(name = "spec_name")),
            @AttributeOverride(name = "version", column = @Column(name = "spec_version")),
            @AttributeOverride(name = "type", column = @Column(name = "spec_type"))
    })
    private ProductSpecification productSpecification;

    private String type;

    @ManyToOne
    @JoinColumn(name = "reserve_product_stock_id")
    private ReserveProductStock reserveProductStock;

    public ReserveProductStockItem() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Quantity getQuantityReserved() {
        return quantityReserved;
    }

    public void setQuantityReserved(Quantity quantityReserved) {
        this.quantityReserved = quantityReserved;
    }

    public Quantity getQuantityRequested() {
        return quantityRequested;
    }

    public void setQuantityRequested(Quantity quantityRequested) {
        this.quantityRequested = quantityRequested;
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

    public ReserveProductStock getReserveProductStock() {
        return reserveProductStock;
    }

    public void setReserveProductStock(ReserveProductStock reserveProductStock) {
        this.reserveProductStock = reserveProductStock;
    }
}
