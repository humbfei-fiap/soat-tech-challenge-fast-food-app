package com.postechfiap_group130.techchallenge_fastfood.core.controllers;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.OrderRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.*;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.OrderStatusEnum;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.PaymentStatusEnum;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;
import com.postechfiap_group130.techchallenge_fastfood.core.interfaces.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    private OrderController orderController;
    private DataSource dataSource;
    private OrderGateway orderGateway;

    @BeforeEach
    void setUp() {
        dataSource = mock(DataSource.class);
        orderGateway = mock(OrderGateway.class);
        orderController = new OrderController(dataSource);
    }


    @Test
    void checkout_success() {
        OrderRequestDto orderRequestDtoMock = new OrderRequestDto(createMockOrderItemDto());
        OrderDto orderDtomock = createMockOrderDto();
        ProductDto productDtoMock = new ProductDto(10L, "Batata Frita",
                "Porçao de batata com queijo", new BigDecimal(25.00),
                CategoryEnum.Category.ACOMPANHAMENTO, Boolean.TRUE);

        when(dataSource.saveOrder(any())).thenReturn(orderDtomock);
        when(dataSource.findById(any())).thenReturn(productDtoMock);

        OrderResponseDto result = orderController.checkout(orderRequestDtoMock);

        assertNotNull(result);
        assertEquals(100L, orderDtomock.id());
        assertEquals(1, orderDtomock.listOrderItemDto().size());

        verify(dataSource).saveOrder(any());
        verify(dataSource, times(2)).findById(any());

    }

    @Test
    void updateStatus_success() {
        OrderDto orderDtomock = createMockOrderDto();
        OrderDto orderDtomockUpdated = createMockOrderDto();
        orderDtomockUpdated.orderStatus().setStatus(OrderStatusEnum.RECEBIDO);

        when(dataSource.findOrderById(any())).thenReturn(orderDtomock);
        when(dataSource.saveOrder(any())).thenReturn(orderDtomockUpdated);

        OrderDto result = orderController.updateStatus(100L, "RECEBIDO");

        assertNotNull(result);
        assertEquals("RECEBIDO", result.orderStatus().name());

        verify(dataSource).saveOrder(any());
        verify(dataSource).findOrderById(any());
    }

    @Test
    void getAllOrdersSorted_success() {
        when(dataSource.getAllOrders()).thenReturn(List.of(createMockOrderDto()));

        List<OrderDto> result = orderController.getAllOrdersSorted();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(dataSource).getAllOrders();
    }

    public static OrderDto createMockOrderDto() {
        OrderItemDto item1 = new OrderItemDto(1l, 10l,1, new BigDecimal("25.00"));

        OrderDto orderDtomock = new OrderDto(
                100L,
                LocalDateTime.now(),
                OrderStatusEnum.CREATED,
                Arrays.asList(item1),
                new BigDecimal("58.00"),
                999L
        );
        return orderDtomock;
    }

    public static List<OrderItemDto> createMockOrderItemDto() {
        OrderItemDto item1 = new OrderItemDto(1l, 10l,1, new BigDecimal("25.00"));
        OrderItemDto item2 = new OrderItemDto(2l, 20l,1, new BigDecimal("8.00"));

        return  Arrays.asList(item1, item2);
    }
}
