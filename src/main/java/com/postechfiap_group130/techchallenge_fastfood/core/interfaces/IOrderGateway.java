package com.postechfiap_group130.techchallenge_fastfood.core.interfaces;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderGateway {
    List<Order> getAllOrders();
    Order save(Order order);
    Order findById(Long orderId);
}
