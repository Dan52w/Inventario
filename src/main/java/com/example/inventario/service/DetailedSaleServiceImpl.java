package com.example.inventario.service;

import com.example.inventario.dto.DetailedSaleMapper;
import com.example.inventario.dto.request.DetailedSaleDto;
import com.example.inventario.dto.response.DetailedSaleDtoGet;
import com.example.inventario.entity.DetailedSale;
import com.example.inventario.repository.DetailedSaleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailedSaleServiceImpl implements DetailedSaleService{
    private DetailedSaleRepository detailedSaleRepository;
    private DetailedSaleMapper detailedSaleMapper;

    public DetailedSaleServiceImpl(DetailedSaleRepository detailedSaleRepository, DetailedSaleMapper detailedSaleMapper) {
        this.detailedSaleRepository = detailedSaleRepository;
        this.detailedSaleMapper = detailedSaleMapper;
    }

    @Override
    public DetailedSaleDto save(DetailedSaleDto detailedSaleDto) {
        DetailedSale detailedSale = detailedSaleMapper.toEntity(detailedSaleDto);
        return detailedSaleMapper.toDto(detailedSaleRepository.save(detailedSale));
    }

    @Override
    public Optional<DetailedSaleDtoGet> findDetailedSaleById(Long id) {
        return detailedSaleRepository.findById(id).map(detailedSaleMapper::toDtoGet);
    }

    @Override
    public Optional<DetailedSaleDto> uptdate(Long id, DetailedSaleDto detailedSaleDto) {
        return detailedSaleRepository.findById(id).map(oldDetailedSale -> {
            oldDetailedSale.setQuantity(detailedSaleDto.quantity());
            oldDetailedSale.setPriceSell(detailedSaleDto.priceSell());
           return detailedSaleMapper.toDto(detailedSaleRepository.save(oldDetailedSale));
        });
    }

    @Override
    public List<DetailedSaleDtoGet> getAllDetailedSale() {
        List<DetailedSale> detailedSales = detailedSaleRepository.findAll();
        return toListDetailedSaleDtoGet(detailedSales);
    }

    private List<DetailedSaleDtoGet> toListDetailedSaleDtoGet(List<DetailedSale> detailedSaleList) {
        List<DetailedSaleDtoGet> detailedSaleDtoList = new ArrayList<>();
        for (DetailedSale detailedSale : detailedSaleList) {
            detailedSaleDtoList.add(detailedSaleMapper.toDtoGet(detailedSale));
        }
        return detailedSaleDtoList;
    }
}
