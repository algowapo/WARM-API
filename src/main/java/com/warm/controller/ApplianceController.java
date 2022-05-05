package com.warm.controller;

import com.warm.exception.Error;
import com.warm.exception.InvalidDataException;
import com.warm.resource.ApplianceRequest;
import com.warm.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("appliances")
public class ApplianceController {
    @Autowired
    ApplianceService applianceService;

    @PostMapping
    public Long create(@Valid @RequestBody ApplianceRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result, Error.INVALID_DATA);
        }
        return applianceService.create(request).getId();
    }
}
