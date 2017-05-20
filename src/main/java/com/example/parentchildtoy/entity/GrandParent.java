package com.example.parentchildtoy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GrandParent extends EntityBase {

    @NotNull(message = "First Name is required")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Age is required")
    @Column(nullable = false)
    private Integer age;

    @Valid
    @OneToMany(mappedBy = "grandParent", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Parent> parents;

    public GrandParent() {
    }

    public GrandParent(String firstName, String lastName, Integer age) {
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

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents != null ? parents : new ArrayList<>();
        this.parents.forEach(p -> p.setGrandParent(this));
    }
}
