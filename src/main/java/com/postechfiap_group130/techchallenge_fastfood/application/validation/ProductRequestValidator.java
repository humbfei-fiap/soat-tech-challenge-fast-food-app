package com.postechfiap_group130.techchallenge_fastfood.application.validation;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeGreaterThan;

import java.math.BigDecimal;
import java.util.Arrays;

import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.ProductRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum;

import br.com.fluentvalidator.AbstractValidator;

public class ProductRequestValidator extends AbstractValidator<ProductRequestDto> {
    @Override
    public void rules() {
        
        setPropertyOnContext(ProductRequestDto.class.getName());

        ruleFor(ProductRequestDto::getName)
                .must(not(stringEmptyOrNull()))
                .withFieldName("name")
                .withMessage("Product name cannot be null or empty")
                .critical();
        ruleFor(ProductRequestDto::getName)
                .must(not(stringSizeGreaterThan(100)))
                .withFieldName("name")
                .withMessage("Product name is too long. Max length is 100")
                .critical();
        ruleFor(ProductRequestDto::getDescription)
                .must(not(stringEmptyOrNull()))
                .withFieldName("description")
                .withMessage("Description cannot be null or empty")
                .critical();
        ruleFor(ProductRequestDto::getPrice)
                .must(not(nullValue()))
                    .withMessage("Price age must be not null")
                    .withFieldName("price")
                .critical();
        ruleFor(ProductRequestDto::getPrice)
                .must(amount -> amount.compareTo(BigDecimal.ZERO) > 0)
                    .withMessage("Price age must be not null")
                    .withFieldName("price")
                .critical();
        ruleFor(ProductRequestDto::getCategory)
                .must(status -> Arrays.asList(CategoryEnum.Category.values()).contains(status))
                .withMessage("Invalid Category value");
    }
}
