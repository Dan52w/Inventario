package com.example.inventario.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DetailedSaleDto(Long id,
                              Long idSale,
                              @NotNull(message = "El producto no puede ser nulo")
                              String productBarCode,
                              @NotNull(message = "La cantidad no puede ser nula")
                              Integer quantity,
                              @NotNull(message = "El precio de venta no puede ser nulo")
                              Double priceSell){
}
