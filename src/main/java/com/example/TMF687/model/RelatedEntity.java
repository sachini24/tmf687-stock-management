package com.example.TMF687.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RelatedEntity {

    @Id
    private Long id;

    private String role;
    private String type;
    private String referredType;

    public RelatedEntity() {}

    public RelatedEntity(Long id, String role, String type, String referredType) {
        this.id = id;
        this.role = role;
        this.type = type;
        this.referredType = referredType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReferredType() {
        return referredType;
    }

    public void setReferredType(String referredType) {
        this.referredType = referredType;
    }
}
