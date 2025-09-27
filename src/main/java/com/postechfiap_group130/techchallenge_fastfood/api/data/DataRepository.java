package com.postechfiap_group130.techchallenge_fastfood.api.data;

import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.CustomerEntity;
import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.CustomerJpaRepository;
import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.OrderEntity;
import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.OrderJpaRepository;
import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.OrderItemEntity;
import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.PaymentEntity;
import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.PaymentJpaRepository;
import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.ProductEntity;
import com.postechfiap_group130.techchallenge_fastfood.api.data.jpa.ProductJpaRepository;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.CustomerDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.OrderItemDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.PaymentDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.ProductCategoryDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.ProductDto;
import com.postechfiap_group130.techchallenge_fastfood.core.interfaces.DataSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataRepository implements DataSource {

    private final CustomerJpaRepository customerJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final PaymentJpaRepository paymentJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataRepository(
            CustomerJpaRepository customerJpaRepository,
            OrderJpaRepository orderJpaRepository,
            PaymentJpaRepository paymentJpaRepository,
            ProductJpaRepository productJpaRepository,
            PasswordEncoder passwordEncoder) {
        this.customerJpaRepository = customerJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.paymentJpaRepository = paymentJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Recebe um DTO e transforma para Entity do JPA para salvar
    //Devolve um DTO
    @Override
    public void saveCustomer(CustomerDto customerDto) {
        String encryptedPassword = passwordEncoder.encode(customerDto.password());

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerDto.id());
        customerEntity.setName(customerDto.name());
        customerEntity.setEmail(customerDto.email());
        customerEntity.setPassword(encryptedPassword);
        customerEntity.setCpf(customerDto.cpf());

        customerJpaRepository.save(customerEntity);

    }

    @Override
    public boolean existsCustomerByEmailOrCpf(String email, String cpf) {

        return customerJpaRepository.existsByEmailOrCpf(email, cpf);
    }

    @Override
    public CustomerDto findCustomerByCpf(String cpf) {
        CustomerEntity entity = customerJpaRepository.findByCpf(cpf);
        if (entity == null) return null;

        CustomerDto customerDto = new CustomerDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getPassword());

        return customerDto;
    }

    @Override
    public OrderDto findOrderById(Long id) {
        Optional<OrderEntity> orderEntity = orderJpaRepository.findById(id);
        if (orderEntity.isEmpty()) return null;

        List<OrderItemDto> orderItemDtoList = orderEntity.get().getItems().stream()
                .map((orderItem) -> new OrderItemDto(
                        orderItem.getId(),
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()))
                .toList();

        return new OrderDto(
                orderEntity.get().getId(),
                orderEntity.get().getOrderDate(),
                orderEntity.get().getOrderStatus(),
                orderItemDtoList,
                orderEntity.get().getTotal(),
                orderEntity.get().getPaymentId());
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderEntity> listOrderEntity = orderJpaRepository.findAll();
        if (listOrderEntity == null) return null;

        List<OrderDto> listOrderDto = listOrderEntity.stream()
                .map(orderEntity -> {
                    List<OrderItemDto> itemDtos = orderEntity.getItems().stream()
                            .map(orderItem -> new OrderItemDto(
                                    orderItem.getId(),
                                    orderItem.getProductId(),
                                    orderItem.getQuantity(),
                                    orderItem.getPrice()))
                            .toList();

                    return new OrderDto(
                            orderEntity.getId(),
                            orderEntity.getOrderDate(),
                            orderEntity.getOrderStatus(),
                            itemDtos,
                            orderEntity.getTotal(),
                            orderEntity.getPaymentId()
                    );
                })
                .toList();

        return listOrderDto;
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId(orderDto.id());
        orderEntity.setOrderDate(orderDto.orderDate());
        orderEntity.setOrderStatus(orderDto.orderStatus());
        orderEntity.setTotal(orderDto.total());

        List<OrderItemEntity> orderItemEntityList = orderDto.listOrderItemDto().stream()
                .map(item -> {
                    OrderItemEntity itemEntity = new OrderItemEntity();
                    itemEntity.setProductId(item.productId());
                    itemEntity.setQuantity(item.quantity());
                    itemEntity.setPrice(item.price());
                    itemEntity.setOrder(orderEntity); // ESSENCIAL!
                    return itemEntity;
                }).toList();

        orderEntity.setItems(orderItemEntityList);

        OrderEntity savedEntity = orderJpaRepository.save(orderEntity);

        return new OrderDto(
                savedEntity.getId(),
                savedEntity.getOrderDate(),
                savedEntity.getOrderStatus(),
                orderDto.listOrderItemDto(),
                savedEntity.getTotal(),
                savedEntity.getPaymentId());
    }

    @Override
    public PaymentDto savePayment(PaymentDto paymentDto) {
        PaymentEntity paymentEntity = new PaymentEntity(
                paymentDto.orderId(),
                paymentDto.amount(),
                paymentDto.status()
        );

        if (paymentDto.id() != null) {
            paymentEntity.setId(paymentDto.id());
        }

        PaymentEntity savedEntity = paymentJpaRepository.save(paymentEntity);

        return new PaymentDto(
                savedEntity.getId(),
                savedEntity.getOrderId(),
                savedEntity.getAmount(),
                savedEntity.getStatus()
        );
    }

    @Override
    public Optional<PaymentDto> findPaymentById(Long paymentId) {
        if (paymentId == null) {
            return Optional.empty();
        }

        return paymentJpaRepository.findById(paymentId)
                .map(entity -> new PaymentDto(
                        entity.getId(),
                        entity.getOrderId(),
                        entity.getAmount(),
                        entity.getStatus()
                ));
    }

    public PaymentDto updatePaymentStatus(PaymentDto paymentDto) {
        PaymentEntity paymentEntity = new PaymentEntity(
                paymentDto.id(),
                paymentDto.orderId(),
                paymentDto.amount(),
                paymentDto.status()
        );
        PaymentEntity savedEntity = paymentJpaRepository.save(paymentEntity);

        return new PaymentDto(
                savedEntity.getId(),
                savedEntity.getOrderId(),
                savedEntity.getAmount(),
                savedEntity.getStatus()
        );
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDto.id());
        productEntity.setName(productDto.name());
        productEntity.setDescription(productDto.description());
        productEntity.setPrice(productDto.price());
        productEntity.setCategory(productDto.category());
        productEntity.setAvaliable(productDto.avaliable());

        productEntity = productJpaRepository.save(productEntity);

        return new ProductDto(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getCategory(),
                productEntity.getAvaliable()
        );
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        this.saveProduct(productDto);
        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        java.util.Optional<ProductEntity> entity = productJpaRepository.findById(id);

        if (entity.isEmpty()) return null;

        ProductDto productDto = new ProductDto(
                entity.get().getId(),
                entity.get().getName(),
                entity.get().getDescription(),
                entity.get().getPrice(),
                entity.get().getCategory(),
                entity.get().getAvaliable());

        return productDto;
    }

    @Override
    public List<ProductDto> findAll() {

        List<ProductEntity> products = productJpaRepository.findAll();
        List<ProductDto> productsDto = products.stream()
                .map(item -> new ProductDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getPrice(),
                        item.getCategory(),
                        item.getAvaliable()
                ))
                .toList();
        return productsDto;
    }

    @Override
    public List<ProductDto> findByCategory(ProductCategoryDto ProductCategoryDto) {
        List<ProductEntity> products = productJpaRepository.findByCategory(ProductCategoryDto.category());
        List<ProductDto> productsDto = products.stream()
                .map(item -> new ProductDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getPrice(),
                        item.getCategory(),
                        item.getAvaliable()
                ))
                .toList();
        return productsDto;
    }
    @Override
    public Boolean existsByName(String name) {
        return productJpaRepository.existsByName(name);
    }
}
