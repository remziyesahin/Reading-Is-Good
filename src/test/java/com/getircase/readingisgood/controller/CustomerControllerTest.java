package com.getircase.readingisgood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getircase.readingisgood.adapters.api.request.CustomerCreationRequest;
import com.getircase.readingisgood.application.ports.incoming.CustomerUseCase;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerUseCase customerUseCase;

    @SneakyThrows
    @Test
    public void it_should_create_new_customer() {

        ObjectMapper mapper = new ObjectMapper();
        CustomerCreationRequest request = mapper.readValue(new ClassPathResource("test/customer-creation-list.json").getFile(), CustomerCreationRequest.class);

        this.mockMvc.perform(post("/api/customer/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeToJson(request)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(customerUseCase).createCustomer(request.toCommand());

    }

    private String serializeToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}