package com.example.TMF687.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
public class Quantity {

    private int amount;
    private String units;

    // No-argument constructor
    public Quantity() {
    }

    // All-arguments constructor
    public Quantity(int amount, String units) {
        this.amount = amount;
        this.units = units;
    }

    // Getters
    public int getAmount() {
        return amount;
    }

    public String getUnits() {
        return units;
    }

    // Setters
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;
        Quantity quantity = (Quantity) o;
        return amount == quantity.amount && Objects.equals(units, quantity.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, units);
    }

    // toString
    @Override
    public String toString() {
        return "Quantity{" +
                "amount=" + amount +
                ", units='" + units + '\'' +
                '}';
    }
}
