package com.example.inventario.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record SaleDto(@NotNull(message = "La fecha de la venta no puede ser nula")
                      LocalDateTime date,
                      @NotNull(message = "El identificador del usuario no puede ser nulo")
                      Long userId,
                      @NotNull(message = "Los detalles de la venta no pueden ser nulos")
                      List<DetailedSaleDto> detailedSales) {
}
