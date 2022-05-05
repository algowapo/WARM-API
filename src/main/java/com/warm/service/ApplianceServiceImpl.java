package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.Appliance;
import com.warm.repository.ApplianceRepository;
import com.warm.repository.UserRepository;
import com.warm.resource.ApplianceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Appliance findById(Long id) {
        return applianceRepository.findById(id).orElseThrow(() -> new ServiceException(Error.APPLIANCE_NOT_FOUND));
    }

    @Override
    public List<Appliance> findAllByUserId(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new ServiceException(Error.USER_NOT_FOUND);
        } else {
            return applianceRepository.findAllByUserId(userId);
        }
    }

    @Override
    public Appliance update(Long id, ApplianceRequest applianceReq) {
        return applianceRepository.findById(id).map(appliance -> {
            appliance.setName(applianceReq.getName());
            appliance.setBrand(applianceReq.getBrand());
            appliance.setModel(applianceReq.getModel());
            appliance.setType(applianceReq.getType());
            return applianceRepository.save(appliance);
        }).orElseThrow(() -> new ServiceException(Error.APPLIANCE_NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return applianceRepository.findById(id).map(appliance -> {
            applianceRepository.delete(appliance);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ServiceException(Error.APPLIANCE_NOT_FOUND));
    }

    @Override
    public Appliance create(ApplianceRequest request) {
        Appliance appliance = fromReq(request);
        appliance.setUser(userService.findById(request.getUserId()));
        return applianceRepository.save(appliance);
    }

    //mapper
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
