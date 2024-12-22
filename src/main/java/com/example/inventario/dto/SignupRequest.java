package com.example.inventario.dto;

import com.example.inventario.entity.Role;

import java.time.LocalDate;
import java.util.Set;

public record SignupRequest(String username,
                            String password,
                            String name,
                            int phone,
                            String address,
                            String roles) {
}
