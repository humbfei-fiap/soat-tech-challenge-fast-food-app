package com.postechfiap_group130.techchallenge_fastfood.core.interfaces;

import java.util.List;


import com.postechfiap_group130.techchallenge_fastfood.core.dtos.CustomerDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.PaymentDto;
import java.util.Optional;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.ProductCategoryDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.ProductDto;

public interface DataSource {
    void saveCustomer(CustomerDto customerDto);

    boolean existsCustomerByEmailOrCpf(String email, String cpf);

    CustomerDto findCustomerByCpf(String cpf);

    OrderDto findOrderById(Long orderId);

    List<OrderDto> getAllOrders();

    OrderDto saveOrder(OrderDto orderDto);

    PaymentDto savePayment(PaymentDto paymentDto);

    Optional<PaymentDto> findPaymentById(Long paymentId);

    PaymentDto updatePaymentStatus(PaymentDto paymentDto);

    //Products
    ProductDto saveProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    ProductDto findById(Long id);
    List<ProductDto> findAll();
    List<ProductDto> findByCategory(ProductCategoryDto ProductCategoryDto);
    Boolean existsByName(String name);
}
