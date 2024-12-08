package com.example.inventario.dto;

import com.example.inventario.dto.request.UserDto;
import com.example.inventario.dto.response.SaleDtoGet;
import com.example.inventario.dto.response.UserDtoGet;
import com.example.inventario.entity.ERole;
import com.example.inventario.entity.Role;
import com.example.inventario.entity.Sale;
import com.example.inventario.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Convertir User a UserDtoGet
    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToStringSet")
    @Mapping(source = "sales", target = "sales", qualifiedByName = "toSaleDtoGetList")
    UserDtoGet toUserDtoGet(User user);

    // Convertir UserDto a User
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringSetToRoles")
    @Mapping(target = "sales", ignore = true)
    User toUser(UserDto userDto);

    // Convertir User a UserDto
    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToStringSet")
    UserDto toUserDto(User user);

    // Métodos auxiliares para manejar la conversión de roles
    @Named("rolesToStringSet")
    default Set<String> rolesToStringSet(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name()) // Convertir ERole a String
                .collect(Collectors.toSet());
    }

    @Named("stringSetToRoles")
    default Set<Role> stringSetToRoles(Set<String> roleNames) {
        return roleNames.stream()
                .map(name -> {
                    Role role = new Role();
                    role.setName(ERole.valueOf(name)); // Convertir String a ERole
                    return role;
                })
                .collect(Collectors.toSet());
    }

    // Convierte Sale a SaleDtoGet
    @Named("toSaleDtoGet")
    default SaleDtoGet toSaleDtoGet(Sale sale) {
        return new SaleDtoGet(
                sale.getId(),
                sale.getDate(),
                sale.getTotalPrice(),
                sale.getUser().getUsername(),  // Obtenemos el username del usuario que realizó la venta
                null // Asumiendo que los detalles de la venta (DetailedSaleDtoGet) se manejan aparte
        );
    }

    // Convierte una lista de Sales a una lista de SaleDtoGet
    @Named("toSaleDtoGetList")
    default List<SaleDtoGet> toSaleDtoGetList(List<Sale> sales) {
        return sales.stream()
                .map(this::toSaleDtoGet)
                .toList();
    }
}
