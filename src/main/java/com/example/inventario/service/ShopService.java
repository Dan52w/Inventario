package com.example.inventario.service;

import com.example.inventario.dto.request.ShopDto;
import com.example.inventario.dto.response.ShopDtoGet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShopService {
    ShopDto save(ShopDto shopDto);
    Optional<ShopDtoGet> findById(Long id);
    Optional<ShopDto> uptdate(Long id, ShopDto shopDto);
    List<ShopDtoGet> findByPurchaseDate(LocalDateTime purchaseDate);
    List<ShopDtoGet> findByQuantity(Integer quantity);
    List<ShopDtoGet> getAllShops();
    void delete(Long id);
}
