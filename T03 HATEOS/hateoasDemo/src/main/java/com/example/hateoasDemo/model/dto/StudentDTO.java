package com.example.hateoasDemo.model.dto;

import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Relation(collectionRelation = "students")
public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private boolean deleted;
    private List<OrderDTO> orders;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
