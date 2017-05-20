package com.example.parentchildtoy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Child extends EntityBase {

    @NotNull(message = "First Name is required")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Age is required")
    @Column(nullable = false)
    private Integer age;

    @NotNull(message = "Parent required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    @Valid
    @OneToMany(mappedBy = "child", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Toy> toys;

    public Child() {
    }

    public Child(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Parent getParent() {
        return parent;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys != null ? toys : new ArrayList<>();
        this.toys.forEach(c -> c.setChild(this));
    }
}
