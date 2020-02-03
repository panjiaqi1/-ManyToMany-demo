package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class C {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<A> aList;

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

    public List<A> getaList() {
        return aList;
    }

    public void setaList(List<A> aList) {
        this.aList = aList;
    }
}
