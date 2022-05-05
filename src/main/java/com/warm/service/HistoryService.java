package com.warm.service;

import com.warm.models.History;
import com.warm.resource.HistoryRequest;

public interface HistoryService {
    History findById(Long id);
    History create(HistoryRequest request);
}
