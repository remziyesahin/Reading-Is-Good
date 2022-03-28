package com.getircase.readingisgood.application.services;

import com.getircase.readingisgood.adapters.persistance.entity.Order;
import com.getircase.readingisgood.application.ports.incoming.StatisticsUseCase;
import com.getircase.readingisgood.application.ports.incoming.dto.OrderStatisticsDto;
import com.getircase.readingisgood.application.ports.outgoing.OrderRepositoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static com.getircase.readingisgood.application.domain.common.BookRetailConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService implements StatisticsUseCase {
    private final OrderRepositoryUseCase orderRepositoryUseCase;

    @Override
    public List<OrderStatisticsDto> getMonthlyOrderStatistics(StatisticCommand statisticCommand) {
        Integer year = LocalDate.now().getYear();
        List<OrderStatisticsDto> orderStatisticsDtos = new ArrayList<>();

        List<Order> orders = orderRepositoryUseCase.findByCustomerIdAndCreatedDateBetween(statisticCommand.getCustomerId(), LocalDate.of(year, FIRST_MONTH_OF_YEAR, FIRST_DAY_OF_MONTH), LocalDate.of(year, LAST_MONTH_OF_YEAR, LAST_DAY_OF_MONTH));
        //Grouping order monthly
        Map<LocalDate, List<Order>> monthlyOrders = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCreatedDate().toLocalDate().with(TemporalAdjusters.firstDayOfMonth())));

        monthlyOrders.entrySet().forEach(monthlyOrder -> {
            OrderStatisticsDto orderStatisticsDto = OrderStatisticsDto.builder()
                    .month(monthlyOrder.getKey().getMonth().getDisplayName(TextStyle.SHORT, Locale.US))
                    .totalBookCount(0)
                    .totalPurchaseAmount(BigDecimal.ZERO)
                    .totalOrderCount(0).build();

            monthlyOrder.getValue().forEach(order -> {
                order.getOrderItems().forEach(orderItem -> {
                    orderStatisticsDto.setTotalBookCount(orderStatisticsDto.getTotalBookCount() + orderItem.getQuantity());
                    orderStatisticsDto.setTotalPurchaseAmount(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())).add(orderStatisticsDto.getTotalPurchaseAmount()));
                });
                orderStatisticsDto.setTotalOrderCount(orderStatisticsDto.getTotalOrderCount() + INCREMENT_ORDER_COUNT);
            });
            orderStatisticsDtos.add(orderStatisticsDto);
        });
        return orderStatisticsDtos;
    }

}
