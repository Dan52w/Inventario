package com.example.inventario.repository;

import com.example.inventario.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByName(String supplierName);
    List<Supplier> findByCity(String city);
    Optional<Supplier> findByIdentification(Long identification);
    List<Supplier> findByIdIn(Collection<Long> ids);
}
