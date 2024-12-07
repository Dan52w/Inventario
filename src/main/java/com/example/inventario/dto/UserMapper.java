package com.example.inventario.dto;

import com.example.inventario.dto.request.UserDto;
import com.example.inventario.dto.response.UserDtoGet;
import com.example.inventario.entity.ERole;
import com.example.inventario.entity.Role;
import com.example.inventario.entity.User;

import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

public interface UserMapper {
    // Convertir User a UserDtoGet
    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToStringSet")
    UserDtoGet toUserDtoGet(User user);

    // Convertir UserDto a User
    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringSetToRoles")
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
}
