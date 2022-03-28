package com.getircase.readingisgood.adapters.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.getircase.readingisgood.application.ports.incoming.OrderUseCase.OrderSearchCommand;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OrderSearchRequest {
    @NotNull(message = "Start date should be filled!")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startDate;

    @NotNull(message = "End date should be filled!")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endDate;

    public OrderSearchCommand toCommand(){
        return new OrderSearchCommand(this.startDate, this.endDate);
    }
}
