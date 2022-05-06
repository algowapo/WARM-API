package com.warm.service;

import com.warm.models.Meter;
import com.warm.resource.MeterDto;
import com.warm.resource.MeterQuery;
import com.warm.resource.MeterRequest;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface MeterService {
    Meter create(MeterRequest request);
    Meter findById(Long id);
    Meter update(Long id, MeterRequest request);
    ResponseEntity<?> delete(Long id);
    List<MeterDto> meterQuery(MeterQuery query);
}
