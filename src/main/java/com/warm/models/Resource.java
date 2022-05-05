package com.warm.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String unit;

    @NotNull
    private Float unitCost;

    @NotNull
    private ResourceType resourceType;

    @OneToOne(mappedBy = "resource")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Meter meter;
}
