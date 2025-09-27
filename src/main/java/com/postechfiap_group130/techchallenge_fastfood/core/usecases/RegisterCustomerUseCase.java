package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.CustomerRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.CustomerGateway;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.ErrorException;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Customer;

import java.util.UUID;

public class RegisterCustomerUseCase {

    private final CustomerGateway customerGateway;

    public RegisterCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer execute(CustomerRequestDto customerRequestDto) {
        boolean customerExists = this.customerGateway.existsByEmailOrCpf(customerRequestDto.getEmail(), customerRequestDto.getCpf());
        if (customerExists) {
            throw new ErrorException("Customer already exists with EMAIL or CPF");
        }
        UUID uuid = UUID.randomUUID();
        Customer customer = new Customer(
                uuid.toString(),
                customerRequestDto.getName(),
                customerRequestDto.getEmail(),
                customerRequestDto.getPassword(),
                customerRequestDto.getCpf());

            this.customerGateway.save(customer);

        return customer;
    }
}
