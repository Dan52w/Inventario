package com.example.inventario.service;

import com.example.inventario.dto.SupplierMapper;
import com.example.inventario.dto.request.SupplierDto;
import com.example.inventario.dto.response.SupplierDtoGet;
import com.example.inventario.entity.Supplier;
import com.example.inventario.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService{
    private SupplierRepository supplierRepository;
    private SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public SupplierDto save(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    public Optional<SupplierDtoGet> findById(Long id) {
        return supplierRepository.findById(id).map(supplierMapper::toSupplierDtoGet);
    }

    @Override
    public Optional<SupplierDtoGet> findByIdentication(Long identification) {
        return supplierRepository.findById(identification).map(supplierMapper::toSupplierDtoGet);
    }

    @Override
    public Optional<SupplierDtoGet> findByName(String name) {
        return supplierRepository.findByName(name).map(supplierMapper::toSupplierDtoGet);
    }

    @Override
    public Optional<SupplierDto> uptdate(Long id, SupplierDto supplierDto) {
        return supplierRepository.findById(id).map(oldSupplier -> {
            oldSupplier.setName(supplierDto.name());
            oldSupplier.setCity(supplierDto.city());
            oldSupplier.setIdentification(supplierDto.identification());
            oldSupplier.setPhone(supplierDto.phone());
            oldSupplier.setAddress(supplierDto.address());
            oldSupplier.setEmail(supplierDto.email());
            return supplierMapper.toDto(supplierRepository.save(oldSupplier));
        });
    }

    @Override
    public List<SupplierDtoGet> findByCity(String city) {
        List<Supplier> suppliers = supplierRepository.findByCity(city);
        return toListSupplierDtoGet(suppliers);
    }

    @Override
    public List<SupplierDtoGet> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return toListSupplierDtoGet(suppliers);
    }

    @Override
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }

    private List<SupplierDtoGet> toListSupplierDtoGet(List<Supplier> suppliers) {
        List<SupplierDtoGet> supplierDtoGes = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierDtoGes.add(supplierMapper.toSupplierDtoGet(supplier));
        }
        return supplierDtoGes;
    }
}
