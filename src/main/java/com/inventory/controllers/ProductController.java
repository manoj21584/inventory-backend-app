package com.inventory.controllers;

import com.inventory.entites.Product;
import com.inventory.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;



    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product product1 = productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long id){
        Product product = productService.getSingleProduct(id);
        return ResponseEntity.ok(product);

    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> product = productService.getAllProducts();
        return ResponseEntity.ok(product);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product,@PathVariable Long id){
        Product product1 = productService.updateProduct(product,id);
        return ResponseEntity.ok(product1);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        String product1 = productService.deleteProduct(id);
        return ResponseEntity.ok(product1);

    }

}
