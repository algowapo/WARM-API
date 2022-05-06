package com.warm.resource;


import com.warm.models.Resource;

public interface MeterDto {

    Long getId();

    String getName();

    String getLocation();

    Float getConsumption();

    Resource getResource();
}
