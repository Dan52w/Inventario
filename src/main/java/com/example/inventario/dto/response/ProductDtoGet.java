package com.example.inventario.dto.response;

public record ProductDtoGet(Long id,
                            String name,
                            String barCode,
                            String description,
                            Double priceBuy,
                            Double priceSell,
                            String image) {
}
