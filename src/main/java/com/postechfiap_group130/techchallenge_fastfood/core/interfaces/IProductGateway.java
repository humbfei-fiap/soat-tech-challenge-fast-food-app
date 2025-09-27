package com.postechfiap_group130.techchallenge_fastfood.core.interfaces;

import java.util.List;

import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Product;

public interface IProductGateway {
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByCategory(Category category);
    Boolean existsByName(String name);
}
