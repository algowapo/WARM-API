package com.warm.resource;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class HistoryRequest {
    private Long id;

    @NotNull(message = "Date cannot be null")
    private Date date;

    @NotNull(message = "Use time cannot be null")
    private Float useTime;

    @NotNull(message = "User cost cannot be null")
    private Float useCost;

    @NotNull(message = "Meter cannot be null")
    private Long meterId;
}
