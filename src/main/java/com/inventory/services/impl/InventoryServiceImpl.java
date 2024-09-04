package com.inventory.services.impl;

import com.inventory.entites.Inventory;

import com.inventory.entites.Product;
import com.inventory.exceptions.ResourceNotFoundException;
import com.inventory.repositories.InventoryRepository;
import com.inventory.repositories.ProductRepository;
import com.inventory.services.InventroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventroyService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Inventory createInvetory(Inventory inventory) {
        Product product = productRepository.findById(inventory.getProduct().getId()).orElseThrow(() -> new ResourceNotFoundException("product","id",inventory.getProduct().getId()));
        inventory.setLastUpdated("not updated yet");
        inventory.setProduct(product);
        productRepository.save(product);
        Inventory save = inventoryRepository.save(inventory);
        System.out.println(save);
        return save;
    }

    @Override
    public List<Inventory> updateInvetory(Long productId, Inventory updatedInventory) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        List<Inventory> inventories = inventoryRepository.findByProductId(productId);

        if (inventories.isEmpty()) {
            throw new ResourceNotFoundException("Inventory", "productId", productId);
        }

        inventories.forEach(inventory -> {
            inventory.setQuantity(updatedInventory.getQuantity());
            inventory.setLastUpdated(String.valueOf(LocalDateTime.now()));
            Inventory save = inventoryRepository.save(inventory);
        });

        return inventories;
    }
    @Override
    public List<Inventory> getAllInventryWithProductId(Long productId) {
        List<Inventory> byProductId = inventoryRepository.findByProductId(productId);

        return byProductId;
    }

    @Override
    public String updateInventoryAndProductWithInventoryId(Long inventoryId, int quantity, Product product) {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new ResourceNotFoundException("inventory", "id", inventoryId));
        inventory.setQuantity(quantity);
        inventory.setLastUpdated(String.valueOf(LocalDateTime.now()));
        Product product1 = inventory.getProduct();
        if(product1==null){
            throw new ResourceNotFoundException("product","id",inventoryId);
        }
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        product1.setStockQuantity(product.getStockQuantity());
        inventoryRepository.save(inventory);
        productRepository.save(product1);

        return "updated successfully";
    }

    @Override
    public Inventory findByIdWithProduct(Long inventoryId) {
        Inventory byIdWithProduct = inventoryRepository.findByIdWithProduct(inventoryId);
        Product product = byIdWithProduct.getProduct();
        System.out.println(product.getName());
        if(byIdWithProduct==null){
            throw new ResourceNotFoundException("inventory","id",inventoryId);
        }
        return byIdWithProduct;
    }

    @Override
    public List<Inventory> getAllInventory() {
        List<Inventory> all = inventoryRepository.findAll();

        return all;
    }

    @Override
    public String deleteInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("inventory", "id", id));
        inventoryRepository.deleteById(id);
        return "deleted successfully";
    }


}
