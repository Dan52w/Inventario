package com.example.inventario.repository;

import com.example.inventario.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByDate(String date);
    List<Sale> findByDateBetween(String date1, String date2);
    List<Sale> findByIdIn(Collection<Long> ids);
    Optional<Sale> findByuserUsername(String username);
}
