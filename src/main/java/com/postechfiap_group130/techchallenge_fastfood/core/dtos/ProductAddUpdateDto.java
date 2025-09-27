package com.postechfiap_group130.techchallenge_fastfood.core.dtos;

import java.math.BigDecimal;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;

public record ProductAddUpdateDto(String name, String description, BigDecimal price, 
                         Category category, boolean avaliable) {
    public ProductAddUpdateDto(String name, String description, BigDecimal price, 
                         Category category, boolean avaliable){
        this.name = name; 
        this.description = description;
        this.price = price;
        this.category = category;
        this.avaliable = avaliable;
    }

}
