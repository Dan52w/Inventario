package com.example.inventario.service;

import com.example.inventario.dto.request.UserDto;
import com.example.inventario.dto.response.UserDtoGet;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto save(UserDto userDto);
    Optional<UserDtoGet> findById(Long id);
    Optional<UserDtoGet> findByUsername(String username);
    Optional<UserDtoGet> findByUsernameAndPassword(String username, String password);
    Optional<UserDto> update(Long id, UserDto userDto);
    List<UserDtoGet> getAllUsers();
    void delete(Long id);
}
