package com.postechfiap_group130.techchallenge_fastfood.core.presenters;

import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderItemDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderItemResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Order;
import java.util.List;

public class OrderPresenter {

    public static List<OrderDto> toDto(List<Order> listOrder) {
        List<OrderDto> orderDtoList = listOrder.stream()
                .map((order -> new OrderDto(
                        order.getId(),
                        order.getOrderDate(),
                        order.getOrderStatus(),
                        List.of(),
                        order.getTotal(),
                        order.getPaymentId())))
                .toList();
        return orderDtoList;
    }

    public static OrderDto toDto(Order order) {
        List<OrderItemDto> orderItemDtoList = order.getItems().stream()
                .map(orderItem -> {
                    return new OrderItemDto(
                        orderItem.getId(),
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()
                    );
                })
                .toList();
        return new OrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getOrderStatus(),
                orderItemDtoList,
                order.getTotal(),
                order.getPaymentId());
    }

    public static OrderDto toDtoWithoutOrderItems(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getOrderStatus(),
                List.of(),
                order.getTotal(),
                order.getPaymentId());
    }

    public static List<OrderDto> toDtoWithOrderItemDtoList(List<Order> listOrder) {
        List<OrderDto> orderDtoList = listOrder.stream()
                .map(order -> {
                    List<OrderItemDto> items = order.getItems().stream()
                            .map(orderItem -> new OrderItemDto(
                                    orderItem.getId(),
                                    orderItem.getProductId(),
                                    orderItem.getQuantity(),
                                    orderItem.getPrice()
                            ))
                            .toList();

                    return new OrderDto(
                            order.getId(),
                            order.getOrderDate(),
                            order.getOrderStatus(),
                            items,
                            order.getTotal(),
                            order.getPaymentId()
                    );
                })
                .toList();

        return orderDtoList;
    }

    public static OrderResponseDto toDtoWithoutOrderItemId(Order order) {
        List<OrderItemResponseDto> orderItemDtoList = order.getItems().stream()
                .map(orderItem -> {
                    return new OrderItemResponseDto(
                            orderItem.getProductId(),
                            orderItem.getQuantity(),
                            orderItem.getPrice()
                    );
                })
                .toList();
        return new OrderResponseDto(
                order.getId(),
                order.getOrderDate(),
                order.getOrderStatus(),
                orderItemDtoList,
                order.getTotal(),
                order.getPaymentId());
    }
}
