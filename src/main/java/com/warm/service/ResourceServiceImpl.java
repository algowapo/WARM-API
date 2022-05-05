package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.Resource;
import com.warm.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Resource findById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new ServiceException(Error.RESOURCE_NOT_FOUND));
    }
}
