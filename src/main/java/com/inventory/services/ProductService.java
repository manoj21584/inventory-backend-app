package com.inventory.services;

import com.inventory.entites.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Product product, Long id);


    String deleteProduct(Long id);

}
