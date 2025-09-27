package com.postechfiap_group130.techchallenge_fastfood.api.data.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String cpf;

}
