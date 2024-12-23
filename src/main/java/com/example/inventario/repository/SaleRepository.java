package com.example.inventario.repository;

import com.example.inventario.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByDate(LocalDateTime date);
    List<Sale> findByDateBetween(LocalDateTime date1, LocalDateTime date2);
    List<Sale> findByIdIn(Collection<Long> ids);
    Optional<Sale> findByuserUsername(String username);
}
