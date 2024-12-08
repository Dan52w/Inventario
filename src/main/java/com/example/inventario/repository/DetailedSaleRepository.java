package com.example.inventario.repository;

import com.example.inventario.entity.DetailedSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DetailedSaleRepository extends JpaRepository<DetailedSale, Long> {
    Optional<DetailedSale> findBySaleId(Long saleId);
    List<DetailedSale> findByIdIn(Collection<Long> ids);
}
