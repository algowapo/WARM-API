package com.warm.resource;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MeterRequest {
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Location cannot be null")
    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Consumption cannot be null")
    private Float consumption;

    @NotNull(message = "Resource cannot be null")
    private Long resourceId;

    @NotNull(message = "Appliance cannot be null")
    private Long applianceId;
}
