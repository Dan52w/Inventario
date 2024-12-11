package com.example.inventario.service;

import java.util.List;
import java.util.Optional;

import com.example.inventario.dto.request.SupplierDto;
import com.example.inventario.dto.response.SupplierDtoGet;

public interface SupplierService {
    SupplierDto save(SupplierDto supplierDto);
    Optional<SupplierDtoGet> findById(Long id);
    Optional<SupplierDtoGet> findByIdentication(Long identification);
    Optional<SupplierDtoGet> findByName(String name);
    Optional<SupplierDto> uptdate(Long id, SupplierDto supplierDto);
    List<SupplierDtoGet> findByCity(String city);
    List<SupplierDtoGet> getAllSuppliers();
    void delete(Long id);
}
