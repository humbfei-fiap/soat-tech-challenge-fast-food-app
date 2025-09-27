package com.postechfiap_group130.techchallenge_fastfood.api.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByEmailOrCpf(String email, String cpf);

    CustomerEntity findByCpf(String cpf);
}
