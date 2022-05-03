package com.warm.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="appliances")
public class Appliance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String brand;

    @NotNull
    @NotBlank
    private String model;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
