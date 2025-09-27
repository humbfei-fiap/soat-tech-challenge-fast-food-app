package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import java.util.List;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Product;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.ProductGateway;

public class GetProductsByCategoryUseCase {
    private final ProductGateway productGateway;

    public GetProductsByCategoryUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public List<Product> execute(Category category){
        List<Product> products = productGateway.findByCategory(category);
        return products;
    }   
}
