package com.example.inventario.dto;

import com.example.inventario.dto.request.ShopDto;
import com.example.inventario.dto.response.ShopDtoGet;
import com.example.inventario.entity.Shop;
import org.mapstruct.Mapping;

public interface ShopMapper {
    // Convierte ShopDto (request) a Shop (entity)
    @Mapping(source = "id", target = "id", ignore = true)
    Shop toEntity(ShopDto shopDto);

    ShopDto toDto(Shop shop);

    // Convierte Shop (entity) a ShopDtoGet (response)
    @Mapping(source = "product.barCode", target = "productBarCode")
    @Mapping(source = "supplier.identification", target = "supplierIdentification")
    @Mapping(source = "user.username", target = "username")
    ShopDtoGet toShopDtoGet(Shop shop);
}
