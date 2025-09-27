package com.postechfiap_group130.techchallenge_fastfood.core.entities;

public enum OrderStatusEnum {

    CREATED,
    RECEBIDO,
    EM_PREPARACAO,
    PRONTO,
    FINALIZADO,
    CANCELADO;

    private OrderStatusEnum status;

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}

