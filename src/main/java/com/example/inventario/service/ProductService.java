package com.example.inventario.service;

import com.example.inventario.dto.request.ProductDto;
import com.example.inventario.dto.response.ProductDtoGet;
import com.example.inventario.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    Optional<ProductDtoGet> findByBarCode(String barcode);
    Optional<ProductDto> uptdate(ProductDto productDto);
    List<ProductDtoGet> findByName(String name);
    List<ProductDtoGet> findByDescription(String description);
    List<ProductDtoGet> findByPriceSellBetween(double min, double max);
    void delete(String barcode);
}
