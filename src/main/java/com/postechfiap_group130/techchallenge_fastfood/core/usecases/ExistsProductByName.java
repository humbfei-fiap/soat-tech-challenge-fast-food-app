package com.postechfiap_group130.techchallenge_fastfood.core.usecases;

import com.postechfiap_group130.techchallenge_fastfood.core.gateways.ProductGateway;

public class ExistsProductByName {
    private final ProductGateway productGateway;

    public ExistsProductByName(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Boolean execute(String name){
        return productGateway.existsByName(name);
    }
}
