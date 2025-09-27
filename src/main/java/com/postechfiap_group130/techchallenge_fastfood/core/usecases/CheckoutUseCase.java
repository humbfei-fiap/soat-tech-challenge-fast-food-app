package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.OrderRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.ProductGateway;

public class CheckoutUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;

    public CheckoutUseCase(OrderGateway orderGateway, ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
    }

    public Order execute(OrderRequestDto orderRequestDto) {
        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(orderGateway, productGateway);

        //Chama o useCase para criar a order
        Order order = createOrderUseCase.execute(orderRequestDto);

        return order;
    }
}
