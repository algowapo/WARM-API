package com.warm.service;

import com.warm.models.Appliance;
import com.warm.resource.ApplianceRequest;

public interface ApplianceService {
    Appliance create(ApplianceRequest appliance);
    Appliance findById(Long id);
}
