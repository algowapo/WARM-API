package com.warm.service;

import com.warm.models.Appliance;
import com.warm.resource.ApplianceRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplianceService {
    Appliance create(ApplianceRequest appliance);
    Appliance findById(Long id);
    List<Appliance> findAllByUserId(Long userId);
    Appliance update(Long id, ApplianceRequest appliance);
    ResponseEntity<?> delete(Long id);
}
