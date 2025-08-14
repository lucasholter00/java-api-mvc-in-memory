package com.booleanuk.api.product.controllers;

import com.booleanuk.api.product.models.Product;
import com.booleanuk.api.product.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository(new ArrayList<>());
    }

    @GetMapping
    public List<Product> getAll(){
        return this.productRepository.getAll();
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable int id){
        Product prod = this.productRepository.getById(id);
        nullCheckForSearch(prod);
        return prod;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        this.productRepository.add(product);
        return product;
    }

    @PutMapping("{id}")
    public Product putProduct(@RequestBody Product product, @PathVariable int id){
        Product foundProd = this.productRepository.getById(id);
        nullCheckForSearch(foundProd);
        foundProd.setName(product.getName());
        foundProd.setCategory(product.getCategory());
        foundProd.setPrice(product.getPrice());

        return foundProd;
    }

    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable int id){
        Product removed = this.productRepository.removeProduct(id);
        nullCheckForSearch(removed);
        return removed;
    }

    private void nullCheckForSearch(Product product){
        if(product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
    }

}
