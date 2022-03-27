package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.OrderUseCase.OrderCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {
    private String orderId;

    public OrderCommand toCommand(){
        return new OrderCommand(this.orderId);
    }

}
