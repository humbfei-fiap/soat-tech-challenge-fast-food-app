package com.postechfiap_group130.techchallenge_fastfood.api.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.postechfiap_group130.techchallenge_fastfood.core.controllers.ProductController;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.DuplicateProductException;
import com.postechfiap_group130.techchallenge_fastfood.application.exceptions.InvalidPropertyProductException;
import com.postechfiap_group130.techchallenge_fastfood.core.entities.CategoryEnum.Category;
import com.postechfiap_group130.techchallenge_fastfood.api.data.DataRepository;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.ProductRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.request.UpdateProductRequestDto;
import com.postechfiap_group130.techchallenge_fastfood.api.rest.dto.response.ProductResponseDto;

@RestController
@RequestMapping("/products")
public class ProductResource {
    
    private final DataRepository dataRepository;
    
    public ProductResource(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> GetProductByCategory(@PathVariable Category category) {
        try {
            ProductController productController = new ProductController(dataRepository);
            List<ProductResponseDto> productResponseDto = productController.getProductsByCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);    
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Category");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductRequestDto produtoDto) throws Exception {
        try {
            ProductController productController = new ProductController(dataRepository);
            ProductResponseDto productResponseDto = productController.createProduct(produtoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);    
        } catch (InvalidPropertyProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DuplicateProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequestDto updateProdutoDto) throws InvalidPropertyProductException {
        try {
            ProductController productController = new ProductController(dataRepository);
            productController.updateProduct(updateProdutoDto);
            return ResponseEntity.status(HttpStatus.OK).body(null);    
        }  catch (InvalidPropertyProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }
}
