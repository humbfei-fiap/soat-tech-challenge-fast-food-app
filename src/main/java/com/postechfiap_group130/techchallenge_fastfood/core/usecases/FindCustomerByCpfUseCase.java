package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Customer;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.CustomerGateway;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.ErrorException;


public class FindCustomerByCpfUseCase {

    private final CustomerGateway customerGateway;

    public FindCustomerByCpfUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer execute(String cpf) {
    Customer customer = customerGateway.findByCpf(cpf);
        if (customer == null) {
            throw new ErrorException("Customer not found with this CPF");
        }
        return customer;
    }
} 