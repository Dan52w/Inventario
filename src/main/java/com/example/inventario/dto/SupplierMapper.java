package com.example.inventario.dto;

import com.example.inventario.dto.request.SupplierDto;
import com.example.inventario.dto.response.SupplierDtoGet;
import com.example.inventario.entity.Product;
import com.example.inventario.entity.Shop;
import com.example.inventario.entity.Supplier;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

public interface SupplierMapper {

    @Mapping(source = "id", target = "id", ignore = true)
    Supplier toEntity(SupplierDto supplierDto);

    SupplierDto toDto(Supplier supplier);

    @Mapping(source = "purchases", target = "product", qualifiedByName = "shopToProductNameList")
    SupplierDtoGet toSupplierDtoGet(Supplier supplier);

    // Convierte Shop a una lista de nombres de productos (usando el nombre del producto en lugar de la entidad completa)
    @Named("shopToProductNameList")
    default List<String> shopToProductNameList(List<Shop> shops) {
        return shops.stream()
                .map(shop -> shop.getProduct().getName())  // Obtener el nombre del producto de la compra
                .toList();
    }
}
