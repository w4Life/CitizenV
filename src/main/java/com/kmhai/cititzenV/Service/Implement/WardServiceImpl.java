package com.kmhai.cititzenV.Service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Payload.WardDTO;
import com.kmhai.cititzenV.Repository.WardRepo;
import com.kmhai.cititzenV.Service.WardService;
import com.kmhai.cititzenV.entity.Ward;

@Service
public class WardServiceImpl implements WardService {
    
    @Autowired
    private WardRepo wardRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<WardDTO> getAll() {
        List<Ward> wards = wardRepo.findAll();
        return wards.stream().map(w -> mapper.map(w, WardDTO.class)).collect(Collectors.toList());
    }

    @Override
    public WardDTO getByCode(String code) {
        return mapper.map(wardRepo.findByCode(code), WardDTO.class);
    }

    @Override
    public List<WardDTO> getByDistrictCode(String code) {
        List<Ward> wards = wardRepo.findByDistrictCode(code);
        return wards.stream().map(w -> mapper.map(w, WardDTO.class)).collect(Collectors.toList());
    }

    public List<WardDTO> getByProvinceCode(String code) {
        List<Ward> wards = wardRepo.findByProvinceCode(code);
        return wards.stream().map(w -> mapper.map(w, WardDTO.class)).collect(Collectors.toList());
    }
}
