package com.postechfiap_group130.techchallenge_fastfood.core.controllers;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.CustomerRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response.CustomerResponseDto;
import com.postechfiap_group130.techchallenge_fastfood.core.dtos.CustomerDto;
import com.postechfiap_group130.techchallenge_fastfood.core.interfaces.DataSource;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.ErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CustomerControllerTest {
    private static final String TEST_CPF = "12345678900";
    private CustomerDto mockCustomerDto;

    DataSource dataSource = Mockito.mock(DataSource.class);
    CustomerController customerController = new CustomerController(dataSource);

    @BeforeEach
    void setUp() {
        mockCustomerDto = new CustomerDto("1", "João da Silva",
                "joao.silva@email.com", TEST_CPF, "123456");
    }

    @Test
    void createCustomerSuccess() {
        // Arrange
        CustomerRequestDto customerRequestDto = new CustomerRequestDto("Jhon", "jhondoe@gmail.com",
                "12345678", "111.111.111-11");

        // Act
        CustomerResponseDto result = customerController.createCustomer(customerRequestDto);

        // Assert
        assertNotNull(result);
        verify(dataSource).existsCustomerByEmailOrCpf(anyString(), anyString());
        verify(dataSource).saveCustomer(any());

    }

    @Test
    void createCustomer_fail_when_customer_exists() {
        // Arrange
        CustomerRequestDto customerRequestDto = new CustomerRequestDto("Jhon", "jhondoe@gmail.com",
                "12345678", "111.111.111-11");

        when(dataSource.existsCustomerByEmailOrCpf(anyString(), anyString())).thenReturn(Boolean.TRUE);

        // Act
        var ex = assertThrows(ErrorException.class, () -> customerController.createCustomer(customerRequestDto));

        // Assert
        assertEquals("Customer already exists with EMAIL or CPF", ex.getMessage());
        verify(dataSource).existsCustomerByEmailOrCpf(anyString(), anyString());

    }

    @Test
    void whenFindByCpf_thenReturnCustomer() {
        // Arrange
        when(dataSource.findCustomerByCpf(TEST_CPF)).thenReturn(mockCustomerDto);

        // Act
        CustomerResponseDto result = customerController.findByCpf(TEST_CPF);

        // Assert
        assertNotNull(result);
        assertEquals(TEST_CPF, result.getCpf());
        assertEquals("João da Silva", result.getName());
        assertEquals("joao.silva@email.com", result.getEmail());
        verify(dataSource, times(1)).findCustomerByCpf(TEST_CPF);
    }

    @Test
    void whenFindByCpf_thenReturnNull() {
        // Arrange
        when(dataSource.findCustomerByCpf(TEST_CPF)).thenReturn(null);

        // Act
        var ex = assertThrows(ErrorException.class, () -> customerController.findByCpf(TEST_CPF));

        // Assert
        assertEquals("Customer not found with this CPF", ex.getMessage());
        verify(dataSource, times(1)).findCustomerByCpf(TEST_CPF);
    }
}