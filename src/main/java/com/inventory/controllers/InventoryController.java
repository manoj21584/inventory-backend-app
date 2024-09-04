package com.inventory.controllers;


import com.inventory.entites.Inventory;
import com.inventory.entites.Product;
import com.inventory.repositories.ProductRepository;
import com.inventory.services.InventroyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventroyService inventroyService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory){
        Inventory invetory = inventroyService.createInvetory(inventory);
        return ResponseEntity.ok(inventory);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<List<Inventory>> updateInventoryByProductId(@PathVariable Long productId,@Valid @RequestBody Inventory inventory ){
        List<Inventory> inventories = inventroyService.updateInvetory(productId, inventory);
        return ResponseEntity.ok(inventories);
    }
    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<String> updateInventoryAndProductWithInventoryId(@PathVariable Long inventoryId, @RequestParam int quantity ,@Valid @RequestBody Product product){
        String invetory = inventroyService.updateInventoryAndProductWithInventoryId(inventoryId,quantity,product);
        return ResponseEntity.ok(invetory);
    }
    @GetMapping("/{productId}")
    public List<Inventory> getAllInventoryWithProductId(@PathVariable Long productId){
        List<Inventory> allInventryWithProductId = inventroyService.getAllInventryWithProductId(productId);
   return ResponseEntity.ok(allInventryWithProductId).getBody();
    }
    @GetMapping("/inventoryAndProduct/{inventoryId}")
    public ResponseEntity<Inventory> getAllInventoryWithProductWithInventoryId(@PathVariable Long inventoryId){
        Inventory invetory = inventroyService.findByIdWithProduct(inventoryId);
        return ResponseEntity.ok(invetory);
    }
    @GetMapping
    public List<Inventory> getAllInventory(){
        List<Inventory> allInventryWithProductId = inventroyService.getAllInventory();
        return ResponseEntity.ok(allInventryWithProductId).getBody();
    }

    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Long id){
        String response= inventroyService.deleteInventory(id);
        return  ResponseEntity.ok(response).getBody();
    }





}
