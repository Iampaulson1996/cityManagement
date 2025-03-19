package com.yaitskiy.citymanagement.service;

import com.yaitskiy.citymanagement.model.Inventory;
import com.yaitskiy.citymanagement.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory addItem(Inventory item) {
        return inventoryRepository.save(item);
    }

    @Transactional
    public Inventory updateItem(UUID itemId, Inventory updatedItem) {
        Inventory existing = inventoryRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Товар с id " + itemId + " не найден"));
        existing.setName(updatedItem.getName());
        existing.setDescription(updatedItem.getDescription());
        existing.setQuantity(updatedItem.getQuantity());
        existing.setPrice(updatedItem.getPrice());
        return inventoryRepository.save(existing);
    }

    public void deleteItem(UUID itemId) {
        inventoryRepository.deleteById(itemId);
    }

    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    public Inventory getItemById(UUID itemId) {
        return inventoryRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Товар с id " + itemId + " не найден"));
    }
}
