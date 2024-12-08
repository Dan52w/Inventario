package com.example.inventario.dto.response;

public record DetailedSaleDtoGet(Long id,
                                 String productName,
                                 Integer quantity,
                                 Double priceSell,
                                 Double subTotal) {
}
