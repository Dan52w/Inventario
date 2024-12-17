package com.example.inventario.repository;

import com.example.inventario.entity.ERole;
import com.example.inventario.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositoy extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
