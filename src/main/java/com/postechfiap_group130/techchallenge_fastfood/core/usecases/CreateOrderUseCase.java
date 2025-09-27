package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.OrderRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.ErrorException;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.OrderItem;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Product;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.ProductGateway;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;

    public CreateOrderUseCase(OrderGateway orderGateway, ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
    }

    public Order execute(OrderRequestDto orderRequestDto) {
        List<OrderItem> items = orderRequestDto.getItems().stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem(item.productId(), item.quantity(), item.price());
                    Product product = productGateway.findById(item.productId());

                    if (product == null) {
                        throw new ErrorException("Product with id " + item.productId() + " not found in database");
                    }

                    return orderItem;
                })
                .collect(Collectors.toList());

        Order newOrder = new Order(items);

        Order savedOrder = orderGateway.save(newOrder);

        return savedOrder;
    }
}
