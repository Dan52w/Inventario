package com.example.inventario.repository;

import com.example.inventario.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByQuantity(Integer quantity);
    List<Shop> findByPurchaseDate(LocalDateTime purchaseDate);
    List<Shop> findByIdIn(Collection<Long> ids);
}
