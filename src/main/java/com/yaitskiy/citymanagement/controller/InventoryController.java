package com.yaitskiy.citymanagement.controller;

import com.yaitskiy.citymanagement.model.Inventory;
import com.yaitskiy.citymanagement.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
@Validated
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Inventory> addItem(@Valid @RequestBody Inventory item) {
        Inventory saved = inventoryService.addItem(item);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Inventory> updateItem(@PathVariable UUID itemId, @Valid @RequestBody Inventory item) {
        Inventory updated = inventoryService.updateItem(itemId, item);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID itemId) {
        inventoryService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllItems() {
        List<Inventory> items = inventoryService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Inventory> getItemById(@PathVariable UUID itemId) {
        Inventory item = inventoryService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }
}
