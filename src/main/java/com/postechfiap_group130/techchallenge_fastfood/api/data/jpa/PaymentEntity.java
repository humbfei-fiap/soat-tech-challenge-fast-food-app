package com.postechfiap_group130.techchallenge_fastfood.api.data.jpa;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;

    public PaymentEntity(Long orderId, BigDecimal amount, PaymentStatusEnum status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }
}
