package com.example.inventario.service;

import com.example.inventario.dto.SaleMapper;
import com.example.inventario.dto.request.SaleDto;
import com.example.inventario.dto.response.SaleDtoGet;
import com.example.inventario.entity.Sale;
import com.example.inventario.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService{
    private SaleRepository saleRepository;
    private SaleMapper saleMapper;

    public SaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    @Override
    public SaleDto save(SaleDto saleDto) {
        Sale sale = saleMapper.toEntity(saleDto);
        return saleMapper.toDto(saleRepository.save(sale));
    }

    @Override
    public Optional<SaleDtoGet> findById(Long id) {
        return saleRepository.findById(id).map(saleMapper::toDtoGet);
    }

    @Override
    public Optional<SaleDtoGet> findByuserUsername(String username) {
        return saleRepository.findByuserUsername(username).map(saleMapper::toDtoGet);
    }

    @Override
    public List<SaleDtoGet> findByDate(LocalDateTime date) {
        List<Sale> sales = saleRepository.findByDate(date);
        return toListSaleDtoGet(sales);
    }

    @Override
    public List<SaleDtoGet> findByDateBetween(LocalDateTime date1, LocalDateTime date2) {
        List<Sale> sales = saleRepository.findByDateBetween(date1, date2);
        return toListSaleDtoGet(sales);
    }

    @Override
    public List<SaleDtoGet> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return toListSaleDtoGet(sales);
    }

    @Override
    public void delete(Long id) {
        saleRepository.deleteById(id);
    }

    private List<SaleDtoGet> toListSaleDtoGet(List<Sale> sales) {
        List<SaleDtoGet> saleDtoGets = new ArrayList<>();
        for (Sale sale : sales) {
            saleDtoGets.add(saleMapper.toDtoGet(sale));
        }
        return saleDtoGets;
    }
}
