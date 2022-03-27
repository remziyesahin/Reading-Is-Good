package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.StatisticsUseCase.StatisticCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticRequest {
    private String customerId;

    public StatisticCommand toCommand(){
        return new StatisticCommand(this.customerId);
    }

}
