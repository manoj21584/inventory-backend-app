package com.inventory.services;


import com.inventory.entites.Inventory;
import com.inventory.entites.Product;

import java.util.List;

public interface InventroyService {
    Inventory createInvetory(Inventory inventory);

    List<Inventory> updateInvetory(Long productId, Inventory inventory);


    List<Inventory> getAllInventryWithProductId(Long productId);

    String updateInventoryAndProductWithInventoryId(Long inventoryId, int quantity, Product product);


    Inventory findByIdWithProduct(Long inventoryId);

    List<Inventory> getAllInventory();

    String deleteInventory(Long id);
}

