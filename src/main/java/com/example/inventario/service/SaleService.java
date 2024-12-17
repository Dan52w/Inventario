package com.example.inventario.service;

import com.example.inventario.dto.request.SaleDto;
import com.example.inventario.dto.response.SaleDtoGet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleService {
    SaleDto save(SaleDto saleDto);
    Optional<SaleDtoGet> findById(Long id);
    Optional<SaleDtoGet> findByuserUsername(String username);
    List<SaleDtoGet> findByDate(LocalDateTime date);
    List<SaleDtoGet> findByDateBetween(LocalDateTime date1, LocalDateTime date2);
    List<SaleDtoGet> getAllSales();
    void delete(Long id);
}
