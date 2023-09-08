package com.kmhai.cititzenV.Service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Payload.DistrictDTO;
import com.kmhai.cititzenV.Repository.DistrictRepo;
import com.kmhai.cititzenV.Service.DistrictService;
import com.kmhai.cititzenV.entity.District;


@Service
public class DistrictServiceImpl implements DistrictService {
    
    @Autowired
    private DistrictRepo districtRepo;

    @Autowired
    private ModelMapper mapper;
    
    @Override
    public List<DistrictDTO> getAll() {
        List<District> districts = districtRepo.findAll();
        return districts.stream().map(d -> mapper.map(d, DistrictDTO.class)).collect(Collectors.toList());
    }

    @Override
    public DistrictDTO getByCode(String code) {
        return mapper.map(districtRepo.findByCode(code), DistrictDTO.class);
    }

    @Override
    public List<DistrictDTO> getByProvinceCode(String code) {
        List<District> districts = districtRepo.findByProvinceCode(code);
        return districts.stream().map(d -> mapper.map(d, DistrictDTO.class)).collect(Collectors.toList());
    }
}
