package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.OrderItem;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.OrderStatusEnum;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.ErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindOrderByIdUseCaseTest {

    private FindOrderByIdUseCase findOrderByIdUseCase;
    private OrderGateway orderGateway;

    @BeforeEach
    void setUp() {
        orderGateway = mock(OrderGateway.class);
        findOrderByIdUseCase = new FindOrderByIdUseCase(orderGateway);
    }

    @Test
    @DisplayName("Must return order when found")
    void shouldReturnOrderWhenFound() {
        Long orderId = 1L;
        List<OrderItem> items = List.of(
            new OrderItem(1L, 1L, 2, new BigDecimal("10.00"))
        );
        Order order = new Order(items);
        order.setId(orderId);
        
        when(orderGateway.findById(orderId)).thenReturn(order);

        Order result = findOrderByIdUseCase.execute(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
        assertEquals(OrderStatusEnum.CREATED, result.getOrderStatus());
        
        verify(orderGateway).findById(orderId);
    }

    @Test
    @DisplayName("Must throw exception when order not found")
    void shouldThrowExceptionWhenOrderNotFound() {
        Long orderId = 999L;
        when(orderGateway.findById(orderId)).thenThrow(new ErrorException("Order not found"));

        assertThrows(ErrorException.class, () -> {
            findOrderByIdUseCase.execute(orderId);
        });
        
        verify(orderGateway).findById(orderId);
    }

    @Test
    @DisplayName("Must throw exception when id is null")
    void shouldThrowExceptionWhenOrderIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            findOrderByIdUseCase.execute(null);
        });
    }
} 