package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class B {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.REMOVE})
    @JoinTable(name = "B_As",
            joinColumns = @JoinColumn(name = "Bs_id"),
            inverseJoinColumns = @JoinColumn(name = "As_id")
    )
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
