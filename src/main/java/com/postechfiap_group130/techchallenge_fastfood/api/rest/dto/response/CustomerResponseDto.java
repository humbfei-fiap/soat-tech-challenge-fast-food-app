package com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private String id;
    private String name;
    private String email;
    private String cpf;
} 