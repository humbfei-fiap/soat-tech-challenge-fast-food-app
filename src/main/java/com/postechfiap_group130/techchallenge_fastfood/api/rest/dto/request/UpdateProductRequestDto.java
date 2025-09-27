package com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request;

import java.math.BigDecimal;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UpdateProductRequestDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private Boolean avaliable;
}
