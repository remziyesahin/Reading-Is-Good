package com.getircase.readingisgood.application.ports.incoming;

import com.getircase.readingisgood.application.ports.incoming.dto.OrderStatisticsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public interface StatisticsUseCase {
    List<OrderStatisticsDto> getMonthlyOrderStatistics(StatisticCommand statisticsCommand);

    @Data
    @AllArgsConstructor
    class StatisticCommand {
        private String customerId;
    }



}
