package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.ErrorException;

public class UpdateOrderStatusUseCase {

    private final OrderGateway orderGateway;

    public UpdateOrderStatusUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order execute(Long orderId, String orderStatus) {

        Order order = orderGateway.findById(orderId);

        if (order == null) {
            throw new ErrorException("Order not found with id " + orderId);
        }

        order.updateStatus(orderStatus);

        Order orderUpdated = orderGateway.save(order);

        return orderUpdated;
    }
}
