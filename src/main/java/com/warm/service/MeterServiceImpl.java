package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.Meter;
import com.warm.repository.MeterRepository;
import com.warm.resource.HistoryRequest;
import com.warm.resource.MeterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MeterServiceImpl implements MeterService {

    @Autowired
    private MeterRepository meterRepository;

    @Autowired
    private ApplianceService applianceService;

    @Autowired
    private ResourceService resourceService;

    //@Autowired
    //private HistoryService historyService;

    @Override
    public Meter findById(Long id) {
        return meterRepository.findById(id).orElseThrow(() -> new ServiceException(Error.METER_NOT_FOUND));
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

        //historyService.create(historyRequest);

        return meter;
    }

    private Meter fromReq(MeterRequest request) {
        Meter meter;
        if (request.getId() != null) {
            meter = findById(request.getId());
        } else {
            meter = Meter.builder().build();
        }

        meter.setName(request.getName());
        meter.setLocation(request.getLocation());
        meter.setConsumption(request.getConsumption());

        return meter;
    }

}
