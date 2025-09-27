package com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request;

import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class OrderRequestDto {
    private List<OrderItemDto> items;
    }
