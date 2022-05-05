package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.Appliance;
import com.warm.repository.ApplianceRepository;
import com.warm.resource.ApplianceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private UserService userService;

    @Override
    public Appliance findById(Long id) {
        return applianceRepository.findById(id).orElseThrow(() -> new ServiceException(Error.APPLIANCE_NOT_FOUND));
    }

    @Override
    public Appliance create(ApplianceRequest request) {
        Appliance appliance = fromReq(request);
        appliance.setUser(userService.findById(request.getUserId()));
        return applianceRepository.save(appliance);
    }


    private Appliance fromReq(ApplianceRequest request) {
        Appliance appliance;
        if (request.getId() != null) {
            appliance = findById(request.getId());
        } else {
            appliance = Appliance.builder().build();
        }
        appliance.setBrand(request.getBrand());
        appliance.setModel(request.getModel());
        appliance.setName(request.getName());
        appliance.setType(request.getType());

        return appliance;
    }
}
