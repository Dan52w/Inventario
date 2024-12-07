package com.example.inventario.dto.response;

import java.util.List;

public record SupplierDtoGet(Long id,
                             String name,
                             Long identification,
                             String address,
                             Integer phone,
                             String email,
                             String city,
                             List<String> product) {
}
