package com.kmhai.cititzenV.Service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Payload.HamletDTO;
import com.kmhai.cititzenV.Repository.HamletRepo;
import com.kmhai.cititzenV.Service.HamletService;
import com.kmhai.cititzenV.entity.Hamlet;


@Service
public class HamletServiceImpl implements HamletService {

    @Autowired
    private HamletRepo hamletRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<HamletDTO> getAll() {
        List<Hamlet> hamlets = hamletRepo.findAll();
        return hamlets.stream().map(w -> mapper.map(w, HamletDTO.class)).collect(Collectors.toList());
    }

    @Override
    public HamletDTO getByCode(String code) {
        return mapper.map(hamletRepo.findByCode(code), HamletDTO.class);
    }

    @Override
    public List<HamletDTO> getByDistrictCode(String code) {
        List<Hamlet> hamlets = hamletRepo.findByDistrictCode(code);
        return hamlets.stream().map(w -> mapper.map(w, HamletDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<HamletDTO> getByProvinceCode(String code) {
        List<Hamlet> hamlets = hamletRepo.findByProvinceCode(code);
        return hamlets.stream().map(w -> mapper.map(w, HamletDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<HamletDTO> getByWardCode(String code) {
        List<Hamlet> hamlets = hamletRepo.findByWardCode(code);
        return hamlets.stream().map(w -> mapper.map(w, HamletDTO.class)).collect(Collectors.toList());
    }
}
