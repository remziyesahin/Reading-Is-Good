package com.getircase.readingisgood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getircase.readingisgood.adapters.api.request.StatisticRequest;
import com.getircase.readingisgood.application.ports.incoming.StatisticsUseCase;
import com.getircase.readingisgood.application.ports.incoming.dto.OrderStatisticsDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsUseCase statisticsUseCase;

    private OrderStatisticsDto orderStatisticsDto;

    @BeforeEach
    void setUp() {
        orderStatisticsDto = OrderStatisticsDto.builder().build();

    }

    @SneakyThrows
    @Test
    void it_should_search_for_monthly_statistic()  {

        String customerId = "623f3f3b76eec951e7e0fddf";
        StatisticRequest statisticRequest = new StatisticRequest(customerId);
        this.mockMvc.perform(get("/api/monthlyStatistic/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeToJson(statisticRequest)))
                .andExpect(status().isOk());

        verify(statisticsUseCase).getMonthlyOrderStatistics(statisticRequest.toCommand());
    }

    private String serializeToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}