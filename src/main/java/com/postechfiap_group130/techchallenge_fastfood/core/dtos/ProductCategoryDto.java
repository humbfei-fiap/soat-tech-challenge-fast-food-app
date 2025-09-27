package com.postechfiap_group130.techchallenge_fastfood.core.dtos;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;

public record ProductCategoryDto(Category category) {
    public ProductCategoryDto(Category category){
        this.category = category;
    }

}
