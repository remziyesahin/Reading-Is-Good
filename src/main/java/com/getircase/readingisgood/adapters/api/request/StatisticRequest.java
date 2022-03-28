package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.StatisticsUseCase.StatisticCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class StatisticRequest {

    @NotNull(message = "Customer Id should be filled!")
    private String customerId;

    public StatisticCommand toCommand(){
        return new StatisticCommand(this.customerId);
    }

}
