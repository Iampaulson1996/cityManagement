package com.yaitskiy.citymanagement.repository;

import com.yaitskiy.citymanagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<com.yaitskiy.citymanagement.model.Inventory, UUID> {
}
