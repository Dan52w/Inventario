package com.example.inventario.dto.response;

import java.util.List;
import java.util.Set;

public record UserDtoGet(Long id,
                         String username,
                         String name,
                         Integer phone,
                         String address,
                         Set<String> roles,
                         List<SaleDtoGet> sales) {
}
