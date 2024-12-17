package com.example.inventario.controllers;

import com.example.inventario.dto.request.ProductDto;
import com.example.inventario.dto.response.ProductDtoGet;
import com.example.inventario.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDtoGet>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<ProductDtoGet> getProductByBarcode(@PathVariable String barcode) {
        return productService.findByBarCode(barcode).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductDtoGet>> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<ProductDtoGet>> getProductByDescription(@PathVariable String description) {
        return ResponseEntity.ok(productService.findByDescription(description));
    }

    @GetMapping("/price/{price1}/{price2}")
    public ResponseEntity<List<ProductDtoGet>> getProductByPrice(@PathVariable double price1, @PathVariable double price2) {
        return ResponseEntity.ok(productService.findByPriceSellBetween(price1, price2));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        ProductDto newProduct = productService.save(productDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.id())
                .toUri();
        return ResponseEntity.created(location).body(newProduct);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Optional<ProductDto> uptdateProduct = productService.uptdate(id, productDto);
        return uptdateProduct.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/barcode/{barcode}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String barcode) {
        productService.delete(barcode);
        return ResponseEntity.noContent().build();
    }
}
