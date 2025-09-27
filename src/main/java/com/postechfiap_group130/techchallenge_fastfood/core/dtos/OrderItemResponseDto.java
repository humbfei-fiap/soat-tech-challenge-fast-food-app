package com.postechfiap_group130.techchallenge_fastfood.core.dtos;

import java.math.BigDecimal;

public record OrderItemResponseDto(Long productId,
                                   Integer quantity,
                                   BigDecimal price) {
}
