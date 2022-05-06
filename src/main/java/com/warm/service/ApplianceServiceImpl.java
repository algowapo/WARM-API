package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.Appliance;
import com.warm.repository.ApplianceRepository;
import com.warm.repository.UserRepository;
import com.warm.resource.ApplianceResource;
import com.warm.resource.SaveApplianceRequest;
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
    public Appliance findByIdAndUserId(Long id, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ServiceException(Error.USER_NOT_FOUND);
        } else {
            return applianceRepository.findById(id).orElseThrow(() -> new ServiceException(Error.APPLIANCE_NOT_FOUND));
        }
    }

    @Override
    public Appliance findById(Long id) {
        return applianceRepository.findById(id).orElseThrow(
                () -> new ServiceException(Error.APPLIANCE_NOT_FOUND));
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
    public Appliance update(Long id, SaveApplianceRequest request) {
        if (!userRepository.existsById(request.getUserId())){
            throw new ServiceException(Error.USER_NOT_FOUND);
        } else {
            return applianceRepository.findById(id).map(appliance -> {
                appliance.setName(request.getName());
                appliance.setBrand(request.getBrand());
                appliance.setModel(request.getModel());
                appliance.setType(request.getType());
                return applianceRepository.save(appliance);
            }).orElseThrow(() -> new ServiceException(Error.APPLIANCE_NOT_FOUND));
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id, Long userId) {
        if (!userRepository.existsById(userId)){
            throw new ServiceException(Error.USER_NOT_FOUND);
        } else{
            return applianceRepository.findById(id).map(appliance -> {
                applianceRepository.delete(appliance);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ServiceException(Error.APPLIANCE_NOT_FOUND));
        }
    }

    @Override
    public Appliance create(SaveApplianceRequest request) {
        if (!userRepository.existsById(request.getUserId())){
            throw new ServiceException(Error.USER_NOT_FOUND);
        } else {
            Appliance appliance = fromReq(request);
            appliance.setUser(userService.findById(request.getUserId()));
            return applianceRepository.save(appliance);
        }
    }

    //mapper
    private Appliance fromReq(SaveApplianceRequest request) {
        Appliance appliance;
        if (request.getId() != null) {
            appliance = findByIdAndUserId(request.getId(), request.getUserId());
        } else {
            appliance = Appliance.builder().build();
        }
        appliance.setBrand(request.getBrand());
        appliance.setModel(request.getModel());
        appliance.setName(request.getName());
        appliance.setType(request.getType());

        return appliance;
    }

    /*
    public ApplianceResource convertToResource(Appliance appliance) {
        ApplianceResource applianceResource = new ApplianceResource();

        applianceResource.setBrand(appliance.getBrand());
        applianceResource.setModel(appliance.getModel());
        applianceResource.setName(appliance.getName());
        applianceResource.setType(appliance.getType());

        return applianceResource;
    }*/
}
