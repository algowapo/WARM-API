package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.Resource;
import com.warm.repository.ResourceRepository;
import com.warm.resource.ResourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    @Lazy
    private MeterService meterService;

    @Override
    public Resource findById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new ServiceException(Error.RESOURCE_NOT_FOUND));
    }

    @Override
    public Resource changeResource(ResourceRequest request) {
        Resource resource = meterService.findById(request.getMeterId()).getResource();
        resource = changeMapper(resource, request);

        return resourceRepository.save(resource);
    }

    private Resource changeMapper(Resource resource, ResourceRequest request) {
        if (request.getUnitCost() != null) {
            resource.setUnitCost(request.getUnitCost());
        }

        if (request.getResourceType() != null) {
            resource.setResourceType(request.getResourceType());
        }

        if (request.getUnit() != null) {
            resource.setUnit(request.getUnit());
        }

        return resource;

    }
}
