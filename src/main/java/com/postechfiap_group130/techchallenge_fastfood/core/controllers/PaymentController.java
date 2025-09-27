package com.postechfiap_group130.techchallenge_fastfood.core.controllers;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.PaymentRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.UpdatePaymentRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.PaymentDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Payment;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.PaymentStatusEnum;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.PaymentGateway;
import com.postechfiap_group130.techchallenge_fastfood.core.interfaces.DataSource;
import com.postechfiap_group130.techchallenge_fastfood.core.presenters.PaymentPresenter;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.CheckPaymentStatusUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.CreatePaymentUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.UpdateOrderStatusUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.UpdatePaymentUseCase;

import java.util.Map;

public class PaymentController {
        private static final Map<PaymentStatusEnum, String> ORDER_STATUS_MAP = Map.of(
                PaymentStatusEnum.APPROVED, "RECEBIDO",
                PaymentStatusEnum.REJECTED, "CANCELADO"
        );

    private final DataSource dataSource;

    public PaymentController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public PaymentDto checkPaymentStatus(Long paymentId) {
        PaymentGateway paymentGateway = new PaymentGateway(dataSource);
        CheckPaymentStatusUseCase checkPaymentStatusUseCase = new CheckPaymentStatusUseCase(paymentGateway);
        Payment payment = checkPaymentStatusUseCase.execute(paymentId);
        return PaymentPresenter.toDto(payment);
    }

    public PaymentDto createPayment(PaymentRequestDto paymentRequestDto) {
        PaymentGateway paymentGateway = new PaymentGateway(dataSource);
        CreatePaymentUseCase createPaymentUseCase = new CreatePaymentUseCase(paymentGateway);
        Payment payment = createPaymentUseCase.execute(paymentRequestDto);
        return PaymentPresenter.toDto(payment);
    }

    public PaymentDto updatePayment(Long paymentId, UpdatePaymentRequestDto updatePaymentRequestDto) {
        PaymentGateway paymentGateway = new PaymentGateway(dataSource);
        OrderGateway orderGateway = new OrderGateway(dataSource);

        UpdatePaymentUseCase updatePaymentUseCase = new UpdatePaymentUseCase(paymentGateway);
        UpdateOrderStatusUseCase updateOrderUseCase = new UpdateOrderStatusUseCase(orderGateway);

        Payment payment = updatePaymentUseCase.execute(paymentId, updatePaymentRequestDto);
        updateOrderUseCase.execute(payment.getOrderId(), ORDER_STATUS_MAP.get(payment.getStatus()));
        return PaymentPresenter.toDto(payment);
    }
}