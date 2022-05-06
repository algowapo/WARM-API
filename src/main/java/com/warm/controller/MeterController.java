package com.warm.controller;

import com.warm.exception.Error;
import com.warm.exception.InvalidDataException;
import com.warm.resource.MeterDto;
import com.warm.resource.MeterQuery;
import com.warm.resource.MeterRequest;
import com.warm.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("meters")
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

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id ,@Valid @RequestBody MeterRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result, Error.INVALID_DATA);
        }
        meterService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id) {
        return meterService.delete(id);
    }

    @GetMapping
    public List<MeterDto> meterQuery(MeterQuery query) {
        return meterService.meterQuery(query);
    }
}
