package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.Meter;
import com.warm.repository.MeterRepository;
import com.warm.resource.HistoryRequest;
import com.warm.resource.MeterDto;
import com.warm.resource.MeterQuery;
import com.warm.resource.MeterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeterServiceImpl implements MeterService {

    @Autowired
    private MeterRepository meterRepository;

    @Autowired
    private ApplianceService applianceService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    @Lazy
    private HistoryService historyService;

    @Override
    public Meter findById(Long id) {
        return meterRepository.findById(id).orElseThrow(() -> new ServiceException(Error.METER_NOT_FOUND));
    }

    @Override
    public Meter update(Long id, MeterRequest request) {
        Meter meter = fromReq(request);
        return meterRepository.save(meter);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Meter meter = findById(id);
        meterRepository.delete(meter);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<MeterDto> meterQuery(MeterQuery query) {
        List<MeterDto> meters = meterRepository.queryMeter(
                query.getId(),
                query.getUserId(),
                query.getResourceType()
        );

        if (meters.isEmpty()) {
            throw  new ServiceException(Error.EMPTY_METER_QUERY);
        }

        return meters;
    }

    @Override
    public Meter create(MeterRequest request) {
        Meter meter = fromReq(request);
        meter.setAppliance(applianceService.findById(request.getApplianceId()));
        meter.setResource(resourceService.findById(request.getResourceId()));

        meter = meterRepository.save(meter);

        //Creamos un history para este meter
        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setDate(new Date());
        historyRequest.setUseTime(Float.parseFloat("0"));
        historyRequest.setUseCost(Float.parseFloat("0"));
        historyRequest.setMeterId(meter.getId());

        historyService.create(historyRequest);

        return meter;
    }

    private Meter fromReq(MeterRequest request) {
        Meter meter;
        if (request.getId() != null) {
            meter = findById(request.getId());
        } else {
            meter = Meter.builder().consumption(Float.parseFloat("0")).build();
        }

        meter.setName(request.getName());
        meter.setLocation(request.getLocation());

        return meter;
    }

}
