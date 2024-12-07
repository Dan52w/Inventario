package com.example.inventario.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ShopDto(Long id,
                      @NotNull(message = "El producto no puede ser nulo")
                      String productBarCode,
                      @NotNull(message = "El proveedor no puede ser nulo")
                      Long supplierIdentification,
                      @NotNull(message = "El usuario no puede ser nulo")
                      String username,
                      @NotNull(message = "La cantidad no puede ser nula")
                      Integer quantity,
                      @NotNull(message = "El precio por unidad no puede ser nulo")
                      Double pricePerUnit,
                      @NotNull(message = "El precio total no puede ser nulo")
                      Double totalPrice,
                      LocalDateTime purchaseDate) {
}
