package com.postechfiap_group130.techchallenge_fastfood.api.rest.controller;

import com.postechfiap_group130.techchallenge_fastfood.api.data.DataRepository;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.UpdatePaymentRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderItemDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.PaymentDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.OrderStatusEnum;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.PaymentStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentResourceTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private PaymentResource paymentResource;

    @Test
    void updatePayment_ShouldReturnOk_WhenUpdateIsSuccessful() {
        // Arrange
        Long paymentId = 1L;
        Long orderId = 2L;
        UpdatePaymentRequestDto requestDto = new UpdatePaymentRequestDto("APPROVED");
        
        // Mock payment DTO with the updated status
        PaymentDto paymentDto = new PaymentDto(
            paymentId,
            orderId,
            new BigDecimal("100.00"),
            PaymentStatusEnum.APPROVED
        );

        // Mock order items
        List<OrderItemDto> orderItems = List.of(
            new OrderItemDto(
                1L,
                1L,
                1,
                new BigDecimal("100.00")
            )
        );

        // Mock order DTO with initial status
        OrderDto orderDto = new OrderDto(
            orderId,
            LocalDateTime.now(),
            OrderStatusEnum.CREATED,
            orderItems,
            new BigDecimal("100.00"),
            paymentId
        );

        // Mock the updated order with RECEBIDO status
        OrderDto updatedOrderDto = new OrderDto(
            orderId,
            orderDto.orderDate(),
            OrderStatusEnum.RECEBIDO,
            orderItems,
            orderDto.total(),
            paymentId
        );

        when(dataRepository.findPaymentById(paymentId)).thenReturn(Optional.of(paymentDto));
        when(dataRepository.updatePaymentStatus(any(PaymentDto.class))).thenReturn(paymentDto);
        when(dataRepository.findOrderById(orderId)).thenReturn(orderDto);
        when(dataRepository.saveOrder(any(OrderDto.class))).thenReturn(updatedOrderDto);

        ResponseEntity<PaymentDto> response = paymentResource.updatePayment(paymentId, requestDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(paymentId, response.getBody().id());
        assertEquals(PaymentStatusEnum.APPROVED, response.getBody().status());
        
        verify(dataRepository).updatePaymentStatus(any(PaymentDto.class));
        verify(dataRepository).saveOrder(any(OrderDto.class));
    }
}
