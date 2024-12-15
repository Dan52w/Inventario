package com.example.inventario.controllers;

import com.example.inventario.dto.request.UserDto;
import com.example.inventario.dto.response.UserDtoGet;
import com.example.inventario.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserDtoGet>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDtoGet> getUserById(@PathVariable Long id) {
        return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDtoGet> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        Optional<UserDtoGet> userDtoGet = userService.findByUsername(userDto.username());
        if (userDtoGet.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "El usuario ya existe", "username", userDto.username()));
        } else {
            return createNewUser(userDto);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        Optional<UserDto> userDtoUpdate = userService.update(id, userDto);
        return userDtoUpdate.map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("username/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        Optional<UserDtoGet> userDtoGet = userService.findByUsername(username);
        if (userDtoGet.isPresent()) {
            userService.delete(userDtoGet.get().id());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "El usuario no existe", "username", username));
        }
    }

    private ResponseEntity<UserDto> createNewUser(UserDto userDto) {
        UserDto newUser = userService.save(userDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.id())
                .toUri();
        return ResponseEntity.created(location).body(newUser);
    }
}
