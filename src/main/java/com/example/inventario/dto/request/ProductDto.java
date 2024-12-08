package com.example.inventario.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductDto(Long id,
                         @NotBlank(message = "El nombre no puede estar vacío")
                         @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
                         String name,
                         @NotBlank(message = "El código de barras no puede estar vacío")
                         @Size(max = 60, message = "El código de barras no puede exceder los 60 caracteres")
                         String barCode,
                         @NotBlank(message = "La descripción no puede estar vacía")
                         @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
                         String description,
                         @NotNull(message = "El precio de compra no puede ser nulo")
                         Double priceBuy,
                         @NotNull(message = "El precio de venta no puede ser nulo")
                         Double priceSell,
                         String image) {
}
