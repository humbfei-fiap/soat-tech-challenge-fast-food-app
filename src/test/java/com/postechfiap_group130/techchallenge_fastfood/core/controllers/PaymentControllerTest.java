package com.postechfiap_group130.techchallenge_fastfood.core.controllers;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.UpdatePaymentRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderItemDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.PaymentDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.OrderStatusEnum;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Payment;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.PaymentStatusEnum;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;
import com.postechfiap_group130.techchallenge_fastfood.core.interfaces.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    private PaymentController paymentController;
    private DataSource dataSource;
    private OrderGateway orderGateway;

    @BeforeEach
    void setUp() {
        dataSource = mock(DataSource.class);
        orderGateway = mock(OrderGateway.class);
        paymentController = new PaymentController(dataSource);
    }

    @Test
    @DisplayName("Must return status APPROVED when payment is approved")
    void shouldReturnApprovedStatus() {
        Long paymentId = 1L;
        when(dataSource.findPaymentById(paymentId)).thenReturn(Optional.of(
            new PaymentDto(paymentId, 1L, new BigDecimal("100.00"), PaymentStatusEnum.APPROVED)
        ));

        PaymentDto result = paymentController.checkPaymentStatus(paymentId);

        assertNotNull(result);
        assertEquals(paymentId, result.id());
        assertEquals(PaymentStatusEnum.APPROVED, result.status());

        verify(dataSource).findPaymentById(paymentId);
    }

    @Test
    @DisplayName("Must return status REJECTED when payment is rejected")
    void shouldReturnRejectedStatus() {
        Long paymentId = 4L;
        when(dataSource.findPaymentById(paymentId)).thenReturn(Optional.of(
            new PaymentDto(paymentId, 1L, new BigDecimal("50.00"), PaymentStatusEnum.REJECTED)
        ));

        PaymentDto result = paymentController.checkPaymentStatus(paymentId);

        assertNotNull(result);
        assertEquals(paymentId, result.id());
        assertEquals(PaymentStatusEnum.REJECTED, result.status());

        verify(dataSource).findPaymentById(paymentId);
    }

    @Test
    @DisplayName("Must return status NOT_FOUND when payment does not exist")
    void shouldReturnNotFoundWhenPaymentDoesNotExist() {
        Long paymentId = 999L;
        when(dataSource.findPaymentById(paymentId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            paymentController.checkPaymentStatus(paymentId);
        });
        
        verify(dataSource).findPaymentById(paymentId);
    }

    @Test
    @DisplayName("Should update payment status successfully")
    void shouldUpdatePaymentStatusSuccessfully() {
        Long paymentId = 1L;
        Long orderId = 2L;
        String newStatus = "APPROVED";
        UpdatePaymentRequestDto requestDto = new UpdatePaymentRequestDto(newStatus);
        
        // Mock the payment DTO that will be returned after update
        PaymentDto paymentDto = new PaymentDto(
            paymentId, 
            orderId, 
            new BigDecimal("100.00"), 
            PaymentStatusEnum.APPROVED
        );

        List<OrderItemDto> orderItems = List.of(
            new OrderItemDto(
                1L,
                1L,
                1,
                new BigDecimal("100.00")
            )
        );


        // Mock the order DTO with initial status
        OrderDto orderDto = new OrderDto(
            orderId, 
            LocalDateTime.now(), 
            OrderStatusEnum.CREATED,
            orderItems,
            new BigDecimal("100.00"), 
            paymentId
        );

        when(dataSource.findPaymentById(paymentId)).thenReturn(Optional.of(paymentDto));
        when(dataSource.updatePaymentStatus(any(PaymentDto.class))).thenReturn(paymentDto);
        when(dataSource.findOrderById(orderId)).thenReturn(orderDto);
        when(dataSource.saveOrder(any(OrderDto.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        PaymentDto result = paymentController.updatePayment(paymentId, requestDto);
        
        assertNotNull(result);
        assertEquals(paymentId, result.id());
        assertEquals(PaymentStatusEnum.APPROVED, result.status());
        
        ArgumentCaptor<PaymentDto> paymentCaptor = ArgumentCaptor.forClass(PaymentDto.class);
        verify(dataSource).updatePaymentStatus(paymentCaptor.capture());
        assertEquals(PaymentStatusEnum.APPROVED, paymentCaptor.getValue().status());
        
        ArgumentCaptor<OrderDto> orderCaptor = ArgumentCaptor.forClass(OrderDto.class);
        verify(dataSource).saveOrder(orderCaptor.capture());
        assertEquals(OrderStatusEnum.RECEBIDO, orderCaptor.getValue().orderStatus());
    }
}