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

@SpringBootApplication
public class ParentChildToyApplication {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext ac = SpringApplication.run(ParentChildToyApplication.class, args)) {
            GrandParentService grandParentService = ac.getBean(GrandParentService.class);

            GrandParent grandParent = new GrandParent("Grand", "Parent", 65);

            Parent parent1 = new Parent("Parent", "One", 30);
            grandParent.setParents(Lists.newArrayList(parent1));

            Child child1 = new Child("Child", "One", 10);
            Toy toy1 = new Toy("Manufacturer 1", "IG Joe Flynn", "new");
            child1.setToys(Lists.newArrayList(toy1));

            Child child2 = new Child("Child", "Two", 8);
            Toy toy2 = new Toy("Manufacturer 1", "IG Joe Base", "gently used");
            child2.setToys(Lists.newArrayList(toy2));

            Child child3 = new Child("Child", "Three", 6);
            Toy toy3 = new Toy("Manufacturer 1", "IG Joe Tank", "rusty");
            child3.setToys(Lists.newArrayList(toy3));

            parent1.setChildren(Lists.newArrayList(child1, child2, child3));

            grandParent = grandParentService.save(grandParent);

            //Split children to a second parent, keeping the grand parent

            Parent parent2 = new Parent("Parent", "Two", 31);
            grandParent.getParents().add(parent2);
            parent1.getChildren().remove(child2);
            parent1.getChildren().remove(child3);

            parent2.setChildren(Lists.newArrayList(child2, child3));
            parent2.setGrandParent(grandParent);

            grandParent = grandParentService.save(grandParent);

            //Split children equally across 3 parents

            Parent parent3 = new Parent("Parent", "Three", 32);
            parent3.setGrandParent(grandParent);
            grandParent.getParents().add(parent3);

            parent2.getChildren().remove(child3);
            parent3.setChildren(Lists.newArrayList(child3));

            grandParentService.save(grandParent);
        }
    }
}
