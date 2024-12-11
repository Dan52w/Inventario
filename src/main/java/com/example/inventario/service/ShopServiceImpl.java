package com.example.inventario.service;

import com.example.inventario.dto.ShopMapper;
import com.example.inventario.dto.request.ShopDto;
import com.example.inventario.dto.response.ShopDtoGet;
import com.example.inventario.entity.Shop;
import com.example.inventario.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
    private ShopRepository shopRepository;
    private ShopMapper shopMapper;

    public ShopServiceImpl(ShopRepository shopRepository, ShopMapper shopMapper) {
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }

    @Override
    public ShopDto save(ShopDto shopDto) {
        Shop shop = shopMapper.toEntity(shopDto);
        return shopMapper.toDto(shopRepository.save(shop));
    }

    @Override
    public Optional<ShopDtoGet> findById(Long id) {
        return shopRepository.findById(id).map(shopMapper::toShopDtoGet);
    }

    @Override
    public Optional<ShopDto> uptdate(Long id, ShopDto shopDto) {
        return shopRepository.findById(id).map(oldShop -> {
            oldShop.setPurchaseDate(shopDto.purchaseDate());
            oldShop.setQuantity(shopDto.quantity());
            oldShop.setPricePerUnit(shopDto.pricePerUnit());
            return shopMapper.toDto(shopRepository.save(oldShop));
        });
    }

    @Override
    public List<ShopDtoGet> findByPurchaseDate(LocalDateTime purchaseDate) {
        List<Shop> shops = shopRepository.findByPurchaseDate(purchaseDate);
        return toListShopDtoGet(shops);
    }

    @Override
    public List<ShopDtoGet> findByQuantity(Integer quantity) {
        List<Shop> shops = shopRepository.findByQuantity(quantity);
        return toListShopDtoGet(shops);
    }

    @Override
    public List<ShopDtoGet> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return toListShopDtoGet(shops);
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }

    private List<ShopDtoGet> toListShopDtoGet(List<Shop> shops) {
        List<ShopDtoGet> shopDtosGets = new ArrayList<>();
        for (Shop shop : shops) {
            shopDtosGets.add(shopMapper.toShopDtoGet(shop));
        }
        return shopDtosGets;
    }
}
