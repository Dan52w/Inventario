package com.example.inventario.controllers;

import com.example.inventario.dto.request.SaleDto;
import com.example.inventario.dto.response.SaleDtoGet;
import com.example.inventario.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping()
    public ResponseEntity<List<SaleDtoGet>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SaleDtoGet> getSaleById(@PathVariable Long id) {
        return saleService.findById(id).map(c -> ResponseEntity.ok(c)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<SaleDtoGet> getSaleByUsername(@PathVariable String username) {
        return saleService.findByuserUsername(username).map(c -> ResponseEntity.ok(c)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/dates/{date1}/{date2}")
    public ResponseEntity<List<SaleDtoGet>> getAllSales(@PathVariable String date1, @PathVariable String date2) {
        return ResponseEntity.ok(saleService.findByDateBetween(date1, date2));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<SaleDtoGet>> getAllSales(@PathVariable String date) {
        return ResponseEntity.ok(saleService.findByDate(date));
    }

    @PostMapping()
    public ResponseEntity<SaleDto> addSale(@RequestBody SaleDto saleDto) {
        SaleDto newSale = saleService.save(saleDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(newSale.userId())
                .toUri();
        return ResponseEntity.created(location).body(newSale);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<SaleDto> deleteSale(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
