package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.OrderUseCase.OrderCreationCommand;
import lombok.Data;

@Data
public class OrderCreationRequest {
    private String bookId;
    private int quantity;

    public OrderCreationCommand toCommand(){
        return new OrderCreationCommand(this.bookId, this.quantity);
    }

}
