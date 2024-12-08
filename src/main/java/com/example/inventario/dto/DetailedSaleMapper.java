package com.example.inventario.dto;

import com.example.inventario.dto.request.DetailedSaleDto;
import com.example.inventario.dto.response.DetailedSaleDtoGet;
import com.example.inventario.entity.DetailedSale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailedSaleMapper {
    // Convierte DetailedSaleDto a DetailedSale
    @Mapping(source = "idSale", target = "sale.id")
    @Mapping(source = "productBarCode", target = "product.barCode")
    @Mapping(source = "id", target = "id", ignore = true)
    DetailedSale toEntity(DetailedSaleDto detailedSaleDto);

    DetailedSaleDto toDto(DetailedSale detailedSale);

    // Convierte DetailedSale a DetailedSaleDtoGet
    @Mapping(source = "product.name", target = "productName")
    DetailedSaleDtoGet toDtoGet(DetailedSale detailedSale);
}
