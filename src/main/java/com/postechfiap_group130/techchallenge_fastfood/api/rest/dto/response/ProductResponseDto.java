package com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response;

import java.math.BigDecimal;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;

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
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private Boolean avaliable;
}
