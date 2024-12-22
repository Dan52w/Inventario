package com.example.inventario.dto;

import java.util.List;

public record JwtResponse(String token, String type, String username, List<String> roles) {
}
