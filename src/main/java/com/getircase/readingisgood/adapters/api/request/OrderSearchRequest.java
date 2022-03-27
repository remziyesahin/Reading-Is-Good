package com.getircase.readingisgood.adapters.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.getircase.readingisgood.application.ports.incoming.OrderUseCase.OrderSearchCommand;

import java.time.LocalDateTime;

public class OrderSearchRequest {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endDate;

    public OrderSearchCommand toCommand(){
        return new OrderSearchCommand(this.startDate, this.endDate);
    }
}
