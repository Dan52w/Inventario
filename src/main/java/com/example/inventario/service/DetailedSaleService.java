package com.example.inventario.service;

import com.example.inventario.dto.request.DetailedSaleDto;
import com.example.inventario.dto.response.DetailedSaleDtoGet;

import java.util.List;
import java.util.Optional;

public interface DetailedSaleService {
    DetailedSaleDto save(DetailedSaleDto detailedSaleDto);
    Optional<DetailedSaleDtoGet> findDetailedSaleById(Long id);
    Optional<DetailedSaleDto> uptdate(DetailedSaleDto detailedSaleDto);
    List<DetailedSaleDtoGet> getAllDetailedSale();
}
