package com.example.inventario.repository;

import com.example.inventario.entity.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Role extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}