package com.warm.service;

import com.warm.models.Resource;
import com.warm.resource.ResourceRequest;

public interface ResourceService {
    Resource findById(Long id);
    Resource changeResource(ResourceRequest request);
}
