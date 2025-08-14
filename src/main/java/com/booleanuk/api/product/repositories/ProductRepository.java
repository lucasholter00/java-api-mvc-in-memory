package com.booleanuk.api.product.repositories;

import com.booleanuk.api.product.models.Product;

import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public List<Product> getAll(){
        return this.products;
    }

    public void add(Product product){ //Void for now, would return db return if connected to db
        this.products.add(product);
    }

    public Product getById(int id){
        for(Product product : this.products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public Product removeProduct(int id){
        Product removeProd = this.getById(id);
        this.products.remove(removeProd);
        return removeProd; //Return null if not found
    }
}
