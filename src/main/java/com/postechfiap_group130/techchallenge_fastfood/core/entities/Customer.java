package com.postechfiap_group130.techchallenge_fastfood.core.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer {
    private String id;
    private String name;
    private String email;
    private String password;
    private String cpf;

    public Customer(String id, String name, String email, String password, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }
}
