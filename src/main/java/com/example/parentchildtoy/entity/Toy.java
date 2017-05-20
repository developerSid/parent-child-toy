package com.example.parentchildtoy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Toy extends EntityBase {

    @NotNull(message = "Manufacturer cannot be null")
    @Column(nullable = false)
    private String manufacturer;

    @NotNull(message = "Name cannot be null")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Condition cannot be null")
    private String condition;

    @NotNull(message = "Child required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    public Toy() {
    }

    public Toy(String manufacturer, String name, String condition) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.condition = condition;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public Child getChild() {
        return child;
    }
}
