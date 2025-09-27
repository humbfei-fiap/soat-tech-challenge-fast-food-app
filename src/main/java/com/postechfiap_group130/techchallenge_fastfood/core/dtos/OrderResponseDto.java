package com.postechfiap_group130.techchallenge_fastfood.core.dtos;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(Long id,
                               LocalDateTime orderDate,
                               OrderStatusEnum orderStatus,
                               List<OrderItemResponseDto> listOrderItemDto,
                               BigDecimal total,
                               Long paymentId) {
}
