package com.inventory.repositories;


import com.inventory.entites.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    @Query("SELECT i FROM Inventory i JOIN FETCH i.product WHERE i.id = :id")
    Inventory findByIdWithProduct(Long id);

    List<Inventory> findByProductId(Long productId);

}
