package com.example.inventario.controllers;

import com.example.inventario.dto.JwtResponse;
import com.example.inventario.dto.LoginRequest;
import com.example.inventario.dto.SignupRequest;
import com.example.inventario.entity.ERole;
import com.example.inventario.entity.Role;
import com.example.inventario.entity.User;
import com.example.inventario.repository.RoleRepositoy;
import com.example.inventario.repository.UserRepository;
import com.example.inventario.segurity.jwt.JwtUtil;
import com.example.inventario.segurity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepositoy roleRepository;

    public AuthenticationController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(),
                        loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken= jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwtToken, "Bearer", userDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest sRequest) {
        // Verificar si el usuario ya existe
        if (userRepository.existsByUsername(sRequest.username())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "El usuario ya está en uso"));
        }
        // Buscar el role en la base de datos utilizando el enum ERole
        Role userRole;
        if(sRequest.roles().equals("ROLE_ADMIN")) {
            userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role no encontrado."));
        }else {
            userRole = roleRepository.findByName(ERole.ROLE_VENDEDOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role no encontrado."));
        }
        // Crear un conjunto de roles y agregar el role de usuario
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        // Crear el usuario
        User user = new User(
                null,
                sRequest.username(),
                passwordEncoder.encode(sRequest.password()),
                sRequest.name(),
                sRequest.phone(),
                sRequest.address(),
                roles, // Asignar los roles
                null);
        // Guardar el usuario en la base de datos
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }
}
