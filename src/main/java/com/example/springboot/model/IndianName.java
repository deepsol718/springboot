package com.example.springboot.model;

import jakarta.persistence.*;


@Entity
@Table(name = "indian_names", schema = "learnspringboot")
public class IndianName {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
    @SequenceGenerator(name = "sq_id", sequenceName = "learnspringboot.indian_names_id_seq", allocationSize = 1)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}

