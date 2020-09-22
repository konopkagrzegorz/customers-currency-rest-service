package com.spring.rest.guru.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
