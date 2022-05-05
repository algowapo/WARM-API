package com.warm.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApplianceResource {
    private Long id;
    private String type;
    private String model;
    private String name;
    private String brand;
}
