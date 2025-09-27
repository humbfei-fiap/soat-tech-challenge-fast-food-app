package com.postechfiap_group130.techchallenge_fastfood.api.rest.controller;

import com.postechfiap_group130.techchallenge_fastfood.api.data.DataRepository;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.CustomerRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response.CustomerResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.ErrorException;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerResourceTest {


    @Mock
    private DataRepository dataRepository;
    @InjectMocks
    private CustomerResource customerResource;

    @Test
    void create_success() {
        // Arrange
        CustomerRequestDto customerRequestDto = new CustomerRequestDto("Jhon", "jhondoe@gmail.com",
                "12345678", "111.111.111-11");
        CustomerResponseDto customerResponseDto = new CustomerResponseDto("ad81af4a-c169-4c6b-be80-6ec5ba3409e1",
                "Jhon", "jhondoe@gmail.com", "111.111.111-11");

        // Act
        ResponseEntity<CustomerResponseDto> result = customerResource.create(customerRequestDto);

        // Assert
        assertEquals(201, result.getStatusCode().value());
    }

    @Test
    void create_when_email_or_cpf_exists() {
        // Arrange
        CustomerRequestDto customerRequestDto = new CustomerRequestDto("Jhon", "jhondoe@gmail.com",
                "12345678", "111.111.111-11");
        CustomerDto mockCustomer = new CustomerDto("ad81af4a-c169-4c6b-be80-6ec5ba3409e1", "Jhon",
                "jhondoe@gmail.com", "111.111.111-11", "123456");

        when(dataRepository.existsCustomerByEmailOrCpf(customerRequestDto.getEmail(), customerRequestDto.getCpf())).thenReturn(Boolean.TRUE);

        // Act
        ErrorException ex = assertThrows(ErrorException.class, () -> customerResource.create(customerRequestDto));

        // Assert
        assertNotNull(ex);
        assertEquals("Customer already exists with EMAIL or CPF", ex.getMessage());
    }

    @Test
    void findByCpf_success() {
        // Arrange
        String cpf = "529.982.247-25";
        CustomerDto mockCustomer = new CustomerDto("ad81af4a-c169-4c6b-be80-6ec5ba3409e1", "Jhon", "jhondoe@gmail.com", cpf, "123456");
        CustomerResponseDto expectedResponse = new CustomerResponseDto("ad81af4a-c169-4c6b-be80-6ec5ba3409e1", "Jhon", "jhondoe@gmail.com", cpf);

        when(dataRepository.findCustomerByCpf(cpf)).thenReturn(mockCustomer);

        // Act
        ResponseEntity<CustomerResponseDto> result = customerResource.findByCpf(cpf);

        // Assert
        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(expectedResponse.getName(), result.getBody().getName());
        assertEquals(expectedResponse.getCpf(), result.getBody().getCpf());
        verify(dataRepository).findCustomerByCpf(cpf);
    }

    @Test
    void findByCpf_notFound() {
        // Arrange
        String cpf = "529.982.247-25";
        when(dataRepository.findCustomerByCpf(cpf)).thenReturn(null);

        // Act
        ErrorException ex = assertThrows(ErrorException.class, () -> customerResource.findByCpf(cpf));

        // Assert
        assertNotNull(ex);
        assertEquals("Customer not found with this CPF", ex.getMessage());
        verify(dataRepository).findCustomerByCpf(cpf);
    }
}
