package com.getircase.readingisgood.adapters.api.controller;

import com.getircase.readingisgood.adapters.api.request.StatisticRequest;
import com.getircase.readingisgood.application.ports.incoming.StatisticsUseCase;
import com.getircase.readingisgood.application.ports.incoming.dto.OrderStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monthlyStatistic")
@Validated
@RequiredArgsConstructor
public class StatisticalReportController {
    private final StatisticsUseCase statisticsUseCase;

    @GetMapping("{customerId}")
    public List<OrderStatisticsDto> getOrderStatistics(@PathVariable String customerId) {
        return statisticsUseCase.getMonthlyOrderStatistics(new StatisticRequest(customerId).toCommand());
    }
}
