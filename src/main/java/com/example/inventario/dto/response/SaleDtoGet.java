package com.example.inventario.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record SaleDtoGet(Long id,
                         LocalDateTime date,
                         Double totalPrice,
                         String username,  // Username del usuario que realizó la venta
                         String userName,  // Nombre del usuario
                         List<DetailedSaleDtoGet> detailedSales) {
}
