package com.warm.service;

import com.warm.models.Appliance;
import com.warm.resource.ApplianceRequest;
import org.springframework.http.ResponseEntity;

public interface ApplianceService {
    Appliance create(ApplianceRequest appliance);
    Appliance findById(Long id);
    Appliance update(Long id, ApplianceRequest appliance);
    ResponseEntity<?> delete(Long id);
}
