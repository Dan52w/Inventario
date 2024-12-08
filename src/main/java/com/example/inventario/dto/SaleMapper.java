package com.example.inventario.dto;

import com.example.inventario.dto.request.SaleDto;
import com.example.inventario.dto.response.SaleDtoGet;
import com.example.inventario.entity.Sale;
import com.example.inventario.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {DetailedSaleMapper.class})
public interface SaleMapper {
    // Convierte SaleDto a Sale (incluye los detalles y usuario)
    @Mapping(target = "id", ignore = true) // El ID será generado automáticamente
    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUser")
    Sale toEntity(SaleDto saleDto);

    SaleDto toDto(Sale sale);

    // Convierte Sale a SaleDtoGet (incluye los detalles y datos del usuario)
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "detailedSales", target = "detailedSales")
    SaleDtoGet toDtoGet(Sale sale);

    // Mapea un ID a una entidad User (debe implementarse en el servicio correspondiente)
    @Named("mapUser")
    default User mapUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }
}
