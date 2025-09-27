package com.postechfiap_group130.techchallenge_fastfood.core.interfaces;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Payment;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.PaymentStatusEnum;

import java.util.List;
import java.util.Optional;

public interface IPaymentGateway {
    Payment save(Payment payment);
    Optional<Payment> findById(Long paymentId);
    Payment checkPaymentStatus(Long paymentId);
    Payment updatePaymentStatus(Payment payment);
} 