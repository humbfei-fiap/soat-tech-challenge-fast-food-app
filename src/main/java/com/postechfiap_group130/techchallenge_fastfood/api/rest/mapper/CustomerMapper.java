package com.postechfiap_group130.techchallenge_fastfood.api.rest.mapper;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.CustomerRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response.CustomerResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toDomain(CustomerRequestDto customerRequestDto) {
        return Customer.builder()
                .name(customerRequestDto.getName())
                .email(customerRequestDto.getEmail())
                .password(customerRequestDto.getPassword())
                .cpf(customerRequestDto.getCpf())
                .build();
    }

    public CustomerResponseDto toResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .cpf(customer.getCpf())
                .build();
    }
}
