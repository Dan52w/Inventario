package com.example.inventario.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SupplierDto(Long id,
                          @NotBlank(message = "El nombre no debe estar vacío")
                          @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
                          String name,
                          @NotNull(message = "La identificacion no puede estar vacia")
                          Long identification,
                          @NotBlank(message = "La dirección no debe estar vacía")
                          @Size(max = 255, message = "La dirección no debe exceder los 255 caracteres")
                          String address,
                          @NotNull(message = "El número de teléfono no puede ser nulo")
                          Integer phone,
                          @NotBlank(message = "El campo 'Email' no debe estar vacío")
                          @Email(message = "formato de correo Email invalido")
                          String email,
                          String city) {
}
