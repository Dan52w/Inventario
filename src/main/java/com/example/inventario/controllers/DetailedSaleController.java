package com.example.inventario.controllers;

import com.example.inventario.dto.request.DetailedSaleDto;
import com.example.inventario.dto.response.DetailedSaleDtoGet;
import com.example.inventario.service.DetailedSaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detailedsale")
public class DetailedSaleController {
    private final DetailedSaleService detailedSaleService;

    public DetailedSaleController(DetailedSaleService detailedSaleService) {
        this.detailedSaleService = detailedSaleService;
    }

    @GetMapping()
    public ResponseEntity<List<DetailedSaleDtoGet>> getDetailedSales() {
        return ResponseEntity.ok(detailedSaleService.getAllDetailedSale());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DetailedSaleDtoGet> getDetailedSaleById(@PathVariable Long id) {
        return detailedSaleService.findDetailedSaleById(id).map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<DetailedSaleDto> addDetailedSale(@RequestBody DetailedSaleDto detailedSaleDto) {
        DetailedSaleDto newDetailedSale = detailedSaleService.save(detailedSaleDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDetailedSale.idSale())
                .toUri();
        return ResponseEntity.created(location).body(newDetailedSale);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<DetailedSaleDto> updateDetailedSale(@PathVariable Long id, @RequestBody DetailedSaleDto detailedSale) {
        Optional<DetailedSaleDto> updateDetailedSale = detailedSaleService.uptdate(id ,detailedSale);
        return updateDetailedSale.map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());
    }
}
