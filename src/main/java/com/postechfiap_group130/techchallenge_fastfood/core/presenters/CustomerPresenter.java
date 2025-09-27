package com.postechfiap_group130.techchallenge_fastfood.core.presenters;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response.CustomerResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Customer;

public class CustomerPresenter {

    public static CustomerResponseDto toDto(Customer customer) {
        return new CustomerResponseDto().builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .cpf(customer.getCpf())
                .build();
    }
}
