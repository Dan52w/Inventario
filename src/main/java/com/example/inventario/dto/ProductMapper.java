package com.example.inventario.dto;

import com.example.inventario.dto.request.ProductDto;
import com.example.inventario.dto.response.ProductDtoGet;
import com.example.inventario.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // Convierte ProductDto (request) a Product (entity)
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDto productDto);

    // Convierte Product (entity) a ProductDtoGet (response)
    ProductDtoGet toDtoGet(Product product);

    // Convierte Product (entity) a ProductDto (request)
    ProductDto toDto(Product product);
}
