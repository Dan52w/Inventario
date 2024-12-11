package com.example.inventario.service;

import com.example.inventario.dto.ProductMapper;
import com.example.inventario.dto.request.ProductDto;
import com.example.inventario.dto.response.ProductDtoGet;
import com.example.inventario.entity.Product;
import com.example.inventario.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public Optional<ProductDtoGet> findByBarCode(String barcode) {
        return productRepository.findByBarCode(barcode).map(productMapper::toDtoGet);
    }

    @Override
    public Optional<ProductDto> uptdate(Long id, ProductDto productDto) {
        return productRepository.findById(id).map(oldProduct -> {
            oldProduct.setName(productDto.name());
            oldProduct.setDescription(productDto.description());
            oldProduct.setBarCode(productDto.barCode());
            oldProduct.setImage(productDto.image());
            oldProduct.setPriceBuy(productDto.priceBuy());
            oldProduct.setPriceSell(productDto.priceSell());
            return productMapper.toDto(productRepository.save(oldProduct));
        });
    }

    @Override
    public List<ProductDtoGet> findByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return toListProductDtoGet(products);
    }

    @Override
    public List<ProductDtoGet> findByDescription(String description) {
        List<Product> products = productRepository.findByDescription(description);
        return toListProductDtoGet(products);
    }

    @Override
    public List<ProductDtoGet> findByPriceSellBetween(double min, double max) {
        List<Product> products = productRepository.findByPriceSellBetween(min, max);
        return toListProductDtoGet(products);
    }

    @Override
    public void delete(String barcode) {
        Long id = productRepository.findByBarCode(barcode).get().getId();
        productRepository.deleteById(id);
    }

    private List<ProductDtoGet> toListProductDtoGet(List<Product> products) {
        List<ProductDtoGet> productDtoGets = new ArrayList<>();
        for (Product product : products) {
            productDtoGets.add(productMapper.toDtoGet(product));
        }
        return productDtoGets;
    }
}
