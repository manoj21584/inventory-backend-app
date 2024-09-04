package com.inventory.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "inventories"})
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "name  must not contain any whitespace")
    private String name;

    @NotBlank(message = "description name cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "description  must not contain any whitespace")
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private double price;

    @NotNull(message = "stockQuantity cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "stockQuantity must be greater than zero")
    private int stockQuantity;


//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
//    @JsonManagedReference

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("product")
    private Set<Inventory> inventories;

    public void addInventory(Inventory inventory) {
        inventories.add(inventory);
        inventory.setProduct(this);
    }

    public void removeInventory(Inventory inventory) {
        inventories.remove(inventory);
        inventory.setProduct(null);
    }


}
