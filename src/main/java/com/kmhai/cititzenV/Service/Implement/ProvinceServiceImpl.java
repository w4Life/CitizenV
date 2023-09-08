package com.kmhai.cititzenV.Service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Payload.ProvinceDTO;
import com.kmhai.cititzenV.Repository.ProvinceRepo;
import com.kmhai.cititzenV.Service.ProvinceService;
import com.kmhai.cititzenV.entity.Province;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepo provinceRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ProvinceDTO> getAll() {
        List<Province> provinces = provinceRepo.findAll();
        return provinces.stream().map(p -> mapper.map(p, ProvinceDTO.class)).collect(Collectors.toList()); 
    }

    @Override
    public ProvinceDTO getByCode(String code) {
        return mapper.map(provinceRepo.findByCode(code), ProvinceDTO.class);
    }
}
