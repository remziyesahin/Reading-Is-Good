package com.getircase.readingisgood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getircase.readingisgood.adapters.api.request.OrderCreationRequest;
import com.getircase.readingisgood.adapters.api.request.OrderRequest;
import com.getircase.readingisgood.adapters.api.request.OrderSearchRequest;
import com.getircase.readingisgood.application.ports.incoming.OrderUseCase;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderUseCase orderUseCase;

    @SneakyThrows
    @Test
    public void it_should_create_new_order() {

        ObjectMapper mapper = new ObjectMapper();
        OrderCreationRequest request = mapper.readValue(new ClassPathResource("test/order-creation-list.json").getFile(), OrderCreationRequest.class);

        this.mockMvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeToJson(request)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(orderUseCase).createOrder(request.toCommand());

    }

    @SneakyThrows
    @Test
    public void it_should_list_existing_order() {
        String orderId = "623f3f3b76eec951e7e0fddf";
        OrderRequest orderRequest = new OrderRequest(orderId);
        this.mockMvc.perform(get("/api/order/{orderId}", orderId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderUseCase).getOrder(orderRequest.toCommand());

    }

    @SneakyThrows
    @Test
    public void it_should_create_search_for_orders() {

        ObjectMapper mapper = new ObjectMapper();
        OrderSearchRequest request = mapper.readValue(new ClassPathResource("test/search-order-list.json").getFile(), OrderSearchRequest.class);

        this.mockMvc.perform(post("/api/order/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeToJson(request)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(orderUseCase).searchOrders(request.toCommand(), getPaging());

    }

    private String serializeToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static Pageable getPaging(){
        return PageRequest.of(0, 3);
    }

}