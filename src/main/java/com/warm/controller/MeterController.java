package com.warm.controller;

import com.warm.exception.Error;
import com.warm.exception.InvalidDataException;
import com.warm.resource.MeterRequest;
import com.warm.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("meter")
public class MeterController {
    @Autowired
    MeterService meterService;

    @PostMapping
    public Long create(@Valid @RequestBody MeterRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result, Error.INVALID_DATA);
        }
        return meterService.create(request).getId();
    }
}
