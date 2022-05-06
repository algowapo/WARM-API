package com.warm.controller;

import com.warm.exception.Error;
import com.warm.exception.InvalidDataException;
import com.warm.resource.ApplianceRequest;
import com.warm.resource.ResourceRequest;
import com.warm.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("resources")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @PatchMapping
    public Long change(@Valid @RequestBody ResourceRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result, Error.INVALID_DATA);
        }
        return resourceService.changeResource(request).getId();
    }
}
