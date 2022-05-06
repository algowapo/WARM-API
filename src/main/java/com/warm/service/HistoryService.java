package com.warm.service;

import com.warm.models.History;
import com.warm.resource.ConsumeDto;
import com.warm.resource.HistoryDto;
import com.warm.resource.HistoryQuery;
import com.warm.resource.HistoryRequest;
import java.util.List;

public interface HistoryService {
    History findById(Long id);
    History create(HistoryRequest request);
    List<HistoryDto> historyQuery(HistoryQuery query);
    ConsumeDto consumeByHistoryQuery(HistoryQuery query);
}
