package com.postechfiap_group130.techchallenge_fastfood.core.dtos;

import java.math.BigDecimal;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;

public record ProductDto(Long id,
                         String name,
                         String description,
                         BigDecimal price,
                         Category category,
                         boolean avaliable) {
}
