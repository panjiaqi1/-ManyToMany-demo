package com.example.demo.entity;

import java.util.List;
import javax.persistence.*;

@Entity
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade ={CascadeType.REMOVE})
    @JoinTable(name="B_As",
            joinColumns=@JoinColumn(name="As_id"),
            inverseJoinColumns=@JoinColumn(name="Bs_id")
    )
    private List<B> bList;

    @ManyToMany(mappedBy = "aList")
    private List<C> cList;

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

    public List<B> getbList() {
        return bList;
    }

    public void setbList(List<B> bList) {
        this.bList = bList;
    }

    public List<C> getcList() {
        return cList;
    }

    public void setcList(List<C> cList) {
        this.cList = cList;
    }
}
