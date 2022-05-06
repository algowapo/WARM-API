package com.warm.service;

import com.warm.exception.Error;
import com.warm.exception.ServiceException;
import com.warm.models.History;
import com.warm.repository.HistoryRepository;
import com.warm.resource.ConsumeDto;
import com.warm.resource.HistoryDto;
import com.warm.resource.HistoryQuery;
import com.warm.resource.HistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    @Override
    public List<HistoryDto> historyQuery(HistoryQuery query) {

        List<HistoryDto> history = historyRepository.queryHistory(
                query.getStartDate(),
                query.getFinishDate(),
                query.getResourceType(),
                query.getUserId(),
                query.getMeterId()
        );

        if (history.isEmpty()) {
            throw new ServiceException(Error.EMPTY_HISTORY_QUERY);
        }

        return history;
    }

    @Override
    public ConsumeDto consumeByHistoryQuery(HistoryQuery query) {
        List<HistoryDto> history = historyQuery(query);

        Float totalConsume = Float.parseFloat("0");
        Float totalTime = Float.parseFloat("0");
        for (HistoryDto h : history) {
            totalConsume += h.getUseCost();
            totalTime += h.getUseTime();
        }

        ConsumeDto consume = new ConsumeDto();
        consume.setTotalConsume(totalConsume);
        consume.setTotalTime(totalTime);

        return consume;
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
