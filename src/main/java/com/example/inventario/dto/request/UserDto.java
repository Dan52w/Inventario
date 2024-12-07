package com.example.inventario.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserDto(Long id,
                      @NotBlank(message = "El nombre de usuario no debe estar vacío")
                      @Size(max = 50, message = "El nombre de usuario no debe exceder los 50 caracteres")
                      String username,
                      @NotBlank(message = "La contraseña no debe estar vacía")
                      @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
                      String password,
                      @NotBlank(message = "El nombre no debe estar vacío")
                      @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
                      String name,
                      @NotNull(message = "El número de teléfono no puede ser nulo")
                      Integer phone,
                      @NotBlank(message = "La dirección no debe estar vacía")
                      @Size(max = 255, message = "La dirección no debe exceder los 255 caracteres")
                      String address,
                      Set<String> roles) {
}
