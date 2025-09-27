package com.postechfiap_group130.techchallenge_fastfood.core.interfaces;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.Customer;

public interface ICustomerGateway {
    void save(Customer customer);

    boolean existsByEmailOrCpf(String email, String cpf);

    Customer findByCpf(String cpf);
}
