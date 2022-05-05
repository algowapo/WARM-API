package com.warm.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ApplianceRequest {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    String name;

    @NotNull(message = "Type cannot be null")
    @NotBlank(message = "Type cannot be blank")
    String type;

    @NotNull(message = "Brand cannot be null")
    @NotBlank(message = "Brand cannot be blank")
    String brand;

    @NotNull(message = "Model cannot be null")
    @NotBlank(message = "Model cannot be blank")
    String model;

    @NotNull(message = "UserId cannot be null")
    Long userId;

    Long id;
}
