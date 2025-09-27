package com.postechfiap_group130.techchallenge_fastfood.core.entities;

public class CategoryEnum {
    
    public enum Category {
        LANCHE,
        BEBIDA,
        ACOMPANHAMENTO,
        SOBREMESA;

        private Category category;

        public Category getCategory() {
            return category;
        }
    }
}

