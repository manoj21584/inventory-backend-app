package com.inventory.services.impl;

import com.inventory.entites.Product;
import com.inventory.exceptions.ResourceNotFoundException;
import com.inventory.repositories.ProductRepository;
import com.inventory.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        Product save = productRepository.save(product);
        return save;
    }

    @Override
    public Product getSingleProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> all = productRepository.findAll();
        return all;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Product productFromDb = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        productFromDb.setName(product.getName());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setStockQuantity(product.getStockQuantity());
        Product save = productRepository.save(productFromDb);

        return save;
    }

    @Override
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        if(product!=null){
            productRepository.deleteById(id);
        }
        return "deleted";

    }


}
