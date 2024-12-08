package com.example.inventario.repository;

import com.example.inventario.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByDescription(String description);
    Optional<Product> findByBarCode(String barcode);
    List<Product> findByPriceSellBetween(double min, double max);
}
