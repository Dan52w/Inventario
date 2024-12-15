package com.example.inventario.controllers;

import com.example.inventario.dto.request.SupplierDto;
import com.example.inventario.dto.response.SupplierDtoGet;
import com.example.inventario.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<SupplierDtoGet>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/city/{city}")
    public ResponseEntity<List<SupplierDtoGet>> getAllSuppliersByCity(@PathVariable String city) {
        return ResponseEntity.ok(supplierService.findByCity(city));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id/{id}")
    public ResponseEntity<SupplierDtoGet> getSupplierById(@PathVariable Long id) {
        return supplierService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/identification/{identification}")
    public ResponseEntity<SupplierDtoGet> getSupplierByIdentification(@PathVariable Long identification) {
        return supplierService.findByIdentication(identification).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/name/{name}")
    public ResponseEntity<SupplierDtoGet> getSupplierByName(@PathVariable String name) {
        return supplierService.findByName(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<SupplierDto> addSupplier(@RequestBody SupplierDto supplierDto) {
        Optional<SupplierDtoGet> supplierDtoGet = supplierService.findByIdentication(supplierDto.identification());
        if (supplierDtoGet.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(supplierDto);
        } else {
            return createNewSupplier(supplierDto);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/id/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto) {
        Optional<SupplierDto> updateSupplierDto = supplierService.uptdate(id, supplierDto);
        return updateSupplierDto.map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());
    }


    private ResponseEntity<SupplierDto> createNewSupplier(SupplierDto supplierDto) {
        SupplierDto newSupplier = supplierService.save(supplierDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newSupplier.id())
                .toUri();
        return ResponseEntity.created(location).body(newSupplier);
    }
}
