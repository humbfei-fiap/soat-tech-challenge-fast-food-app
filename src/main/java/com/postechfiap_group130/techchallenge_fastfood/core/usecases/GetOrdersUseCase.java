package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;

import java.util.List;

public class GetOrdersUseCase {

    private final OrderGateway orderGateway;

    public GetOrdersUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public List<Order> execute() {
        List<Order> result = orderGateway.getAllOrders();

        if (result == null) return null;

        return result;
    };
}
