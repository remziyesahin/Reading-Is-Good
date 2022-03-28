package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.OrderUseCase.OrderCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OrderRequest {
    @NotNull(message = "Order Id should be filled!")
    private String orderId;

    public OrderCommand toCommand(){
        return new OrderCommand(this.orderId);
    }

}
