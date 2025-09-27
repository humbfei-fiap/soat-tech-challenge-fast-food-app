package com.postechfiap_group130.techchallenge_fastfood.api.data.jpa;

import java.math.BigDecimal;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@Getter
@Setter
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean avaliable;

    public static ProductEntity fromEntity(Product produto) {
        return new ProductEntity(
            produto.getId(),
            produto.getName(),
            produto.getDescription(),
            produto.getPrice(),
            produto.getCategory(),
            produto.getAvaliable()
        );
    }
    
    public Product toDomain() {
        return new Product(id, name, description, price, category, avaliable);
    }
}
