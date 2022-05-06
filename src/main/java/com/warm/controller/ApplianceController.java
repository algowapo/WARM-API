package com.warm.controller;

import com.warm.exception.Error;
import com.warm.exception.InvalidDataException;
import com.warm.models.Appliance;
import com.warm.resource.ApplianceResource;
import com.warm.resource.SaveApplianceRequest;
import com.warm.service.ApplianceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users/{userId}/appliances")
public class ApplianceController {
    @Autowired
    ApplianceService applianceService;

    @Autowired
    ModelMapper mapper;

    @PostMapping
    public Long create(@Valid @RequestBody SaveApplianceRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result, Error.INVALID_DATA);
        }
        return applianceService.create(request).getId();
    }

    @GetMapping("/{id}")
    public ApplianceResource getApplianceByIdAndUserId(@PathVariable(name = "id") Long id,
                                                       @PathVariable(name = "userId") Long userId){
        return convertToResource(applianceService.findByIdAndUserId(id, userId));
    }

    

    @GetMapping
    public List<ApplianceResource> getAllAppliancesByUserId(@PathVariable(name = "userId") Long userId){
        return applianceService.findAllByUserId(userId).stream().
                map(this::convertToResource).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ApplianceResource updateAppliance(@PathVariable(name = "id") Long id,
                                             @Valid @RequestBody SaveApplianceRequest request){
        return convertToResource(applianceService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAppliance(@PathVariable(name = "id") Long id,
                                             @PathVariable(name = "userId") Long userId){
        return applianceService.delete(id, userId);
    }

    private Appliance convertToEntity(SaveApplianceRequest resource) { return mapper.map(resource, Appliance.class); }

    private ApplianceResource convertToResource(Appliance entity) { return mapper.map(entity, ApplianceResource.class); }
}
