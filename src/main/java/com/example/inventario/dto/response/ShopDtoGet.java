package com.example.inventario.dto.response;

import java.time.LocalDateTime;

public record ShopDtoGet(Long id,
                         String productName,
                         String supplierName,
                         String username,
                         Integer quantity,
                         Double pricePerUnit,
                         Double totalPrice,
                         LocalDateTime purchaseDate) {
}
