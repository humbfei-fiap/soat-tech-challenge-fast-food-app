package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.ProductRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.InvalidPropertyProductException;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.Product;
import com.postechfiap_group130.techchallenge_fastfood.core.gateways.ProductGateway;

public class RegisterProductUseCase {
    private final ProductGateway productGateway;

    public RegisterProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(ProductRequestDto productRequestDto) throws InvalidPropertyProductException{
        Product product = new Product(productRequestDto.getName(), productRequestDto.getDescription(), productRequestDto.getPrice(), productRequestDto.getCategory());
        product = productGateway.saveProduct(product);
        return product;
    }
}
