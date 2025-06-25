package com.example.TMF687.model;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProductSpecification {
    private String specificationId;
    private String name;
    private String version;
    private String type;

    // No-argument constructor
    public ProductSpecification() {
    }

    // All-arguments constructor
    public ProductSpecification(String specificationId, String name, String version, String type) {
        this.specificationId = specificationId;
        this.name = name;
        this.version = version;
        this.type = type;
    }

    // Getters
    public String getId() {
        return specificationId;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setId(String id) {
        this.specificationId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setType(String type) {
        this.type = type;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSpecification)) return false;
        ProductSpecification that = (ProductSpecification) o;
        return Objects.equals(specificationId, that.specificationId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(version, that.version) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specificationId, name, version, type);
    }

    // toString()
    @Override
    public String toString() {
        return "ProductSpecification{" +
                "specificationId='" + specificationId + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
