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
public class Parent extends EntityBase {

    @NotNull(message = "First Name is required")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Age is required")
    @Column(nullable = false)
    private Integer age;

    @NotNull(message = "Grand parent required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grand_parent_id", nullable = false)
    private GrandParent grandParent;

    @Valid
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Child> children;

    public Parent() {
    }

    public Parent(String firstName, String lastName, Integer age) {
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

    public void setGrandParent(GrandParent grandParent) {
        this.grandParent = grandParent;
    }

    public GrandParent getGrandParent() {
        return grandParent;
    }

    public List<Child> getChildren() {
        if (children == null) {
            this.children = new ArrayList<>();
        }

        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children != null ? children : new ArrayList<>();
        this.children.forEach(c -> c.setParent(this));
    }
}
