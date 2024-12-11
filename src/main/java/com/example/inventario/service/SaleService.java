package com.example.inventario.service;

import com.example.inventario.dto.request.SaleDto;
import com.example.inventario.dto.response.SaleDtoGet;
import com.example.inventario.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    SaleDto save(SaleDto saleDto);
    Optional<SaleDtoGet> findById(Long id);
    Optional<SaleDtoGet> findByuserUsername(String username);
    List<SaleDtoGet> findByDate(String date);
    List<SaleDtoGet> findByDateBetween(String date1, String date2);
    List<SaleDtoGet> getAllSales();
    void delete(Long id);
}
