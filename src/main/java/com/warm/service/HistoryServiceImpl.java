package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.History;
import com.warm.repository.HistoryRepository;
import com.warm.resource.HistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private MeterService meterService;

    @Override
    public History findById(Long id) {
        return historyRepository.findById(id).orElseThrow(() -> new ServiceException(Error.HISTORY_NOT_FOUND));
    }

    @Override
    public History create(HistoryRequest request) {
        History history = fromReq(request);
        history.setMeter(meterService.findById(request.getMeterId()));

        return historyRepository.save(history);
    }

    private History fromReq(HistoryRequest request) {
        History history;
        if (request.getId() != null) {
            history = findById(request.getId());
        } else {
            history = History.builder().build();
        }

        history.setDate(request.getDate());
        history.setUseCost(request.getUseCost());
        history.setUseTime(request.getUseTime());

        return history;
    }
}
