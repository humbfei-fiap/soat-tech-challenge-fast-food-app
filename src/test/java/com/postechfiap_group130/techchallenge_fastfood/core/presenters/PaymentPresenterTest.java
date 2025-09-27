package com.postechfiap_group130.techchallenge_fastfood.core.presenters;

import com.postechfiap_group130.techchallenge_fastfood.core.dtos.PaymentDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Payment;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.PaymentStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentPresenterTest {

    @Test
    @DisplayName("Must convert payment to dto")
    void shouldConvertPaymentToDto() {
        Long paymentId = 1L;
        Long orderId = 1L;
        Payment payment = new Payment(orderId, new BigDecimal("25.50"));
        payment.setId(paymentId);
        payment.setStatus(PaymentStatusEnum.APPROVED);

        PaymentDto result = PaymentPresenter.toDto(payment);

        assertNotNull(result);
        assertEquals(paymentId, result.id());
        assertEquals(PaymentStatusEnum.APPROVED, result.status());
    }
} 