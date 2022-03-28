package com.getircase.readingisgood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getircase.readingisgood.adapters.api.request.BookCreationRequest;
import com.getircase.readingisgood.adapters.api.request.BookUpdateRequest;
import com.getircase.readingisgood.application.ports.incoming.BookUseCase;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookUseCase bookUseCase;

    @SneakyThrows
    @Test
    public void it_should_create_book() {

        ObjectMapper mapper = new ObjectMapper();
        BookCreationRequest request = mapper.readValue(new ClassPathResource("test/book-creation-list.json").getFile(), BookCreationRequest.class);

        this.mockMvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeToJson(request)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(bookUseCase).createBook(request.toCommand());

    }

    private String serializeToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}