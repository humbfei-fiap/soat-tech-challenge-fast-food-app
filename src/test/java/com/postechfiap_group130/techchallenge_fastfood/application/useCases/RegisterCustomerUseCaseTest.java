package com.postechfiap_group130.techchallenge_fastfood.application.useCases;

//Talvez nao seja necessario pois ja estamos testando em CustomerControllerTest que ja chama as camadas internas
class RegisterCustomerUseCaseTest {

//    private final CustomerGateway customerGateway = mock(CustomerGateway.class);
//    private final RegisterCustomerUseCase registerCustomerUseCase = new RegisterCustomerUseCase(customerGateway);
//
//    @Test
//    void execute_success() {
//        // Arrange
//        CustomerRequestDto customerRequestDto = new CustomerRequestDto("Jhon", "jhondoe@gmail.com",
//                "12345678", "111.111.111-11");
//
//        when(customerGateway.existsByEmailOrCpf(anyString(), anyString())).thenReturn(Boolean.FALSE);
//
//        // Act
//        Customer result = registerCustomerUseCase.execute(customerRequestDto);
//
//        // Assert
//        assertNotNull(result);
//        verify(customerGateway).existsByEmailOrCpf(anyString(), anyString());
//        verify(customerGateway).save(any());
//    }
//
//    @Test
//    void execute_when_customer_exists() {
//        // Arrange
//        CustomerRequestDto customerRequestDto = new CustomerRequestDto("Jhon", "jhondoe@gmail.com",
//                "12345678", "111.111.111-11");
//
//        when(customerGateway.existsByEmailOrCpf(anyString(), anyString())).thenReturn(Boolean.TRUE);
//
//        // Act
//        var ex = assertThrows(ErrorException.class, () -> registerCustomerUseCase.execute(customerRequestDto));
//
//        // Assert
//        assertEquals("Customer already exists with EMAIL or CPF", ex.getMessage());
//        verify(customerGateway).existsByEmailOrCpf(anyString(), anyString());
//    }
}