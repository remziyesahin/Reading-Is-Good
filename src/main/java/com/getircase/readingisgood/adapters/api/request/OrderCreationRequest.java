package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.OrderUseCase.OrderCreationCommand;
import com.getircase.readingisgood.application.ports.incoming.dto.OrderDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreationRequest {
    private List<OrderDto> orders;

    public OrderCreationCommand toCommand(){
        return new OrderCreationCommand(this.orders);
    }

}
