package com.postechfiap_group130.techchallenge_fastfood.api.rest.controller;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.CustomerRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response.CustomerResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.api.data.DataRepository;
import com.postechfiap_group130.techchallenge_fastfood.core.controllers.CustomerController;
import com.postechfiap_group130.techchallenge_fastfood.application.validation.CpfValidator;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.InvalidCpfException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

    private final DataRepository dataRepository;

    public CustomerResource(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponseDto> create(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        CustomerController customerController = new CustomerController(dataRepository);
        CustomerResponseDto result = customerController.createCustomer(customerRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDto> findByCpf(@PathVariable String cpf) {
        if (!CpfValidator.isValid(cpf)) {
            throw new InvalidCpfException("Invalid CPF: " + cpf);
        }

        CustomerController customerController = new CustomerController(dataRepository);
        CustomerResponseDto response = customerController.findByCpf(cpf);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

}
