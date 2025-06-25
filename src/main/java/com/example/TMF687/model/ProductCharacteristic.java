package com.example.TMF687.model;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProductCharacteristic {

    private String name;
    private String value;
    private String valueType;

    public ProductCharacteristic() {
    }

    public ProductCharacteristic( String name, String value, String valueType) {
        this.name = name;
        this.value = value;
        this.valueType = valueType;
    }

    // Getters


    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getValueType() {
        return valueType;
    }

    // Setters


    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    // equals() and hashCode() for proper comparison and use in sets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCharacteristic)) return false;
        ProductCharacteristic that = (ProductCharacteristic) o;
        return
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(valueType, that.valueType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, valueType);
    }

    // toString() method
    @Override
    public String toString() {
        return "ProductCharacteristic{" +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", valueType='" + valueType + '\'' +
                '}';
    }
}
