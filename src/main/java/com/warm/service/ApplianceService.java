package com.warm.service;

import com.warm.models.Appliance;
import com.warm.resource.ApplianceResource;
import com.warm.resource.SaveApplianceRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplianceService {
    Appliance create(SaveApplianceRequest appliance);
    Appliance findByIdAndUserId(Long id, Long userId);
    Appliance findById(Long id);
    List<Appliance> findAllByUserId(Long userId);
    Appliance update(Long id, SaveApplianceRequest appliance);
    ResponseEntity<?> delete(Long id, Long userId);
}
