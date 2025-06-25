package com.example.TMF687.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class RelatedParty {

    @Id
    private Long id;

    private String role;
    private String type;
    private String referredType;

    // No-argument constructor
    public RelatedParty() {
    }

    // All-argument constructor
    public RelatedParty(Long id, String role, String type, String referredType) {
        this.id = id;
        this.role = role;
        this.type = type;
        this.referredType = referredType;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getType() {
        return type;
    }

    public String getReferredType() {
        return referredType;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReferredType(String referredType) {
        this.referredType = referredType;
    }

    // equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelatedParty)) return false;
        RelatedParty that = (RelatedParty) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(role, that.role) &&
                Objects.equals(type, that.type) &&
                Objects.equals(referredType, that.referredType);
    }

    // hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(id, role, type, referredType);
    }

    // toString()
    @Override
    public String toString() {
        return "RelatedParty{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", type='" + type + '\'' +
                ", referredType='" + referredType + '\'' +
                '}';
    }
}
