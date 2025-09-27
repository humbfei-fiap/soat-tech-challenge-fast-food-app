package com.postechfiap_group130.techchallenge_fastfood.application.useCases;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Customer;
import com.postechfiap_group130.techchallenge_fastfood.core.usecases.FindCustomerByCpfUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Talvez nao seja necessario pois ja estamos testando em CustomerControllerTest que ja chama as camadas internas
@ExtendWith(MockitoExtension.class)
class FindCustomerByCpfUseCaseTest {

//    @Mock
//    private CustomerRepository customerRepository;
//
//    @InjectMocks
//    private FindCustomerByCpfUseCase findCustomerByCpfUseCase;
//
//    private Customer mockCustomer;
//    private static final String TEST_CPF = "12345678900";
//
//    @BeforeEach
//    void setUp() {
//        mockCustomer = Customer.builder()
//                .id("1")
//                .name("João da Silva")
//                .cpf(TEST_CPF)
//                .email("joao.silva@email.com")
//                .build();
//    }
//
//    @Test
//    void whenFindByCpf_thenReturnCustomer() {
//        // Arrange
//        when(customerRepository.findByCpf(TEST_CPF)).thenReturn(mockCustomer);
//
//        // Act
//        Customer result = findCustomerByCpfUseCase.execute(TEST_CPF);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(TEST_CPF, result.getCpf());
//        assertEquals("João da Silva", result.getName());
//        assertEquals("joao.silva@email.com", result.getEmail());
//        verify(customerRepository, times(1)).findByCpf(TEST_CPF);
//    }
//
//    @Test
//    void whenFindByCpf_thenReturnNull() {
//        // Arrange
//        when(customerRepository.findByCpf(TEST_CPF)).thenReturn(null);
//
//        // Act
//        Customer result = findCustomerByCpfUseCase.execute(TEST_CPF);
//
//        // Assert
//        assertNull(result);
//        verify(customerRepository, times(1)).findByCpf(TEST_CPF);
//    }
} 