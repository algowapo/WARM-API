package com.warm.controller;

import com.warm.resource.HistoryDto;
import com.warm.resource.HistoryQuery;
import com.warm.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("histories")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public List<HistoryDto> findHistoryDtoByQuery(HistoryQuery query) {
        return historyService.historyQuery(query);
    }
}
