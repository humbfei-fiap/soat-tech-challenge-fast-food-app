package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;

public class FindOrderByIdUseCase {

    private final OrderGateway orderGateway;

    public FindOrderByIdUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order execute(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        Order order = orderGateway.findById(orderId);

        return order;
    }
} 