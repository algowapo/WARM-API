package com.warm.resource;

import java.util.Date;

public interface HistoryDto {
    Long getId();

    Date getDate();

    Float getUseTime();

    Float getUseCost();
}
