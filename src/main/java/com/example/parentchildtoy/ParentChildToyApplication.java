package com.example.parentchildtoy;

import com.example.parentchildtoy.entity.Child;
import com.example.parentchildtoy.entity.GrandParent;
import com.example.parentchildtoy.entity.Parent;
import com.example.parentchildtoy.entity.Toy;
import com.example.parentchildtoy.service.GrandParentService;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

@SpringBootApplication
public class ParentChildToyApplication {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext ac = SpringApplication.run(ParentChildToyApplication.class, args)) {
            GrandParentService grandParentService = ac.getBean(GrandParentService.class);

            GrandParent grandParent = new GrandParent("Grand", "Parent", 65);

            Parent parent0 = new Parent("Parent", "One", 30);
            grandParent.setParents(Lists.newArrayList(parent0));

            Child child0 = new Child("Child", "One", 10);
            Toy toy0 = new Toy("Manufacturer 1", "IG Joe Flynn", "new");
            child0.setToys(Lists.newArrayList(toy0));

            Child child1 = new Child("Child", "Two", 8);
            Toy toy1 = new Toy("Manufacturer 1", "IG Joe Base", "gently used");
            child1.setToys(Lists.newArrayList(toy1));

            Child child2 = new Child("Child", "Three", 6);
            Toy toy2 = new Toy("Manufacturer 1", "IG Joe Tank", "rusty");
            child2.setToys(Lists.newArrayList(toy2));

            parent0.setChildren(Lists.newArrayList(child0, child1, child2));

            grandParent = grandParentService.save(grandParent);

            //Split children to a second parent, keeping the grand parent

            Parent parent1 = new Parent("Parent", "Two", 31);
            grandParent.getParents().add(parent1);
            grandParent.getParents().get(0).getChildren().removeIf(c -> Objects.equals(c.getId(), child1.getId()));
            grandParent.getParents().get(0).getChildren().removeIf(c -> Objects.equals(c.getId(), child2.getId()));

            parent1.setChildren(Lists.newArrayList(child1, child2));
            parent1.setGrandParent(grandParent);

            grandParent = grandParentService.update(grandParent);

            //Split children equally across 3 parents

            Parent parent2 = new Parent("Parent", "Three", 32);
            grandParent.getParents().add(parent2);
            grandParent.getParents().get(1).getChildren().removeIf(c -> Objects.equals(c.getId(), child2.getId()));
            grandParent.getParents().get(2).setChildren(Lists.newArrayList(child2));
            grandParent.getParents().get(2).setGrandParent(grandParent);
            grandParent.getParents().add(parent2);

            grandParent = grandParentService.update(grandParent);

            System.out.println(grandParent);
        }
    }
}
