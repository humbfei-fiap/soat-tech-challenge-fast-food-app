package com.postechfiap_group130.techchallenge_fastfood.core.controllers;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.CustomerRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response.CustomerResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.core.interfaces.DataSource;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.FindCustomerByCpfUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.RegisterCustomerUseCase;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.CustomerGateway;
import com.postechfiap_group130.techchallenge_fastfood.core.presenters.CustomerPresenter;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Customer;

public class CustomerController {

    private final DataSource dataSource;

    public CustomerController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        //Instancia um CustomerGateway que sera injetado para useCase utilizar
        //Instancia um RegisterCustomerUseCase e executa o useCase passando o DTO com os dados
        CustomerGateway customerGateway = new CustomerGateway(dataSource);
        RegisterCustomerUseCase registerCustomerUseCase = new RegisterCustomerUseCase(customerGateway);

        Customer customer = registerCustomerUseCase.execute(customerRequestDto);

        //Presenter que sera utilizado para formatar os dados (mapper)
        return CustomerPresenter.toDto(customer);
    }

    public CustomerResponseDto findByCpf(String cpf) {
        CustomerGateway customerGateway = new CustomerGateway(dataSource);
        FindCustomerByCpfUseCase findCustomerByCpfUseCase = new FindCustomerByCpfUseCase(customerGateway);

        Customer customer = findCustomerByCpfUseCase.execute(cpf);

        return CustomerPresenter.toDto(customer);

    }
}
