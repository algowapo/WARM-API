package com.warm.controller;

import com.warm.exception.Error;
import com.warm.exception.InvalidDataException;
import com.warm.models.Appliance;
import com.warm.resource.ApplianceResource;
import com.warm.resource.SaveApplianceRequest;
import com.warm.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("appliances")
public class ApplianceController {
    @Autowired
    ApplianceService applianceService;

    @PostMapping
    public Long create(@Valid @RequestBody SaveApplianceRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result, Error.INVALID_DATA);
        }
        return applianceService.create(request).getId();
    }

    @GetMapping("/{id}")
    public ApplianceResource getApplianceById(@PathVariable(name = "id") Long id){
        return applianceService.convertToResource(applianceService.findById(id));
    }

    @PutMapping("/{id}")
    public ApplianceResource updateAppliance(@PathVariable(name = "id") Long id,
                                             @Valid @RequestBody SaveApplianceRequest request){
        return applianceService.convertToResource(applianceService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAppliance(@PathVariable(name = "id") Long id){
        return applianceService.delete(id);
    }

}
