package com.postechfiap_group130.techchallenge_fastfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderItem {

    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

    public OrderItem(Long productId, Integer quantity, BigDecimal price) {
        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("price must be greater than zero");
        }

        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    //Retorna o subtotal referente ao produto, multiplicando o preço pela quantidade
    //Importante para quando for calcular o total em Order
    public BigDecimal getSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}