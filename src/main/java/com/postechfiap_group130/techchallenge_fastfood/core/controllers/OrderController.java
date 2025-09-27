package com.postechfiap_group130.techchallenge_fastfood.core.controllers;

import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.ProductGateway;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.OrderRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.CheckoutUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.GetAllOrdersSortedUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.GetOrdersUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.FindOrderByIdUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.OrderGateway;
import com.postechfiap_group130.techchallenge_fastfood.core.interfaces.DataSource;
import com.postechfiap_group130.techchallenge_fastfood.core.presenters.OrderPresenter;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.UpdateOrderStatusUseCase;

import java.util.List;

public class OrderController {

    private final DataSource dataSource;

    public OrderController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<OrderDto> getAllOrders() {
        OrderGateway orderGateway = new OrderGateway(dataSource);
        GetOrdersUseCase getOrdersUseCase = new GetOrdersUseCase(orderGateway);

        List<Order> listOrders = getOrdersUseCase.execute();

        return OrderPresenter.toDto(listOrders);
    }

    public OrderResponseDto checkout(OrderRequestDto orderRequestDto) {
        OrderGateway orderGateway = new OrderGateway(dataSource);
        ProductGateway productGateway = new ProductGateway(dataSource);
        CheckoutUseCase checkoutUseCase = new CheckoutUseCase(orderGateway, productGateway);

        Order result = checkoutUseCase.execute(orderRequestDto);

        return OrderPresenter.toDtoWithoutOrderItemId(result);
    }

    public OrderDto updateStatus(Long orderId, String orderStatus) {
        OrderGateway orderGateway = new OrderGateway(dataSource);
        UpdateOrderStatusUseCase updateOrderStatusUseCase = new UpdateOrderStatusUseCase(orderGateway);

        Order result = updateOrderStatusUseCase.execute(orderId, orderStatus);

        return OrderPresenter.toDtoWithoutOrderItems(result);
    }

    public OrderDto findById(Long orderId) {
        OrderGateway orderGateway = new OrderGateway(dataSource);
        FindOrderByIdUseCase findOrderByIdUseCase = new FindOrderByIdUseCase(orderGateway);

        Order order = findOrderByIdUseCase.execute(orderId);

        return OrderPresenter.toDto(order);
    }

    public List<OrderDto> getAllOrdersSorted() {
        OrderGateway orderGateway = new OrderGateway(dataSource);
        GetAllOrdersSortedUseCase getAllOrdersSortedUseCase = new GetAllOrdersSortedUseCase(orderGateway);

        List<Order> listOrders = getAllOrdersSortedUseCase.execute();

        return OrderPresenter.toDtoWithOrderItemDtoList(listOrders);
    }
}
