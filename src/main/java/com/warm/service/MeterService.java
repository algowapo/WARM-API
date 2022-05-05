package com.warm.service;

import com.warm.models.Meter;
import com.warm.resource.MeterRequest;

public interface MeterService {
    Meter create(MeterRequest request);
    Meter findById(Long id);
}
