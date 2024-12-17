package com.example.inventario.controllers;

import com.example.inventario.dto.request.ShopDto;
import com.example.inventario.dto.response.ShopDtoGet;
import com.example.inventario.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public ResponseEntity<List<ShopDtoGet>> getAllShops() {
        return ResponseEntity.ok(shopService.getAllShops());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ShopDtoGet> getShopById(@PathVariable Long id) {
        return shopService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ShopDtoGet>> getShopsByDate(@PathVariable String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return ResponseEntity.ok(shopService.findByPurchaseDate(localDateTime));
    }

    @GetMapping("/quantity/{quantity}")
    public ResponseEntity<List<ShopDtoGet>> getShopsByQuantity(@PathVariable Integer quantity) {
        return ResponseEntity.ok(shopService.findByQuantity(quantity));
    }

    @PostMapping()
    public ResponseEntity<ShopDto> addShop(@RequestBody ShopDto shopDto) {
        ShopDto newshop = shopService.save(shopDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newshop.id())
                .toUri();
        return ResponseEntity.created(location).body(newshop);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ShopDto> updateShop(@PathVariable Long id, @RequestBody ShopDto shopDto) {
        Optional<ShopDto> updatedshop = shopService.uptdate(id, shopDto);
        return updatedshop.map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping()
    public ResponseEntity<ShopDto> deleteShop(@PathVariable Long id) {
        shopService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
