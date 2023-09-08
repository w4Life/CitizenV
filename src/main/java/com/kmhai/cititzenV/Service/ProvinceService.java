package com.kmhai.cititzenV.Service;

import java.util.List;

import com.kmhai.cititzenV.Payload.ProvinceDTO;

public interface ProvinceService {
    
    List<ProvinceDTO> getAll();
    ProvinceDTO getByCode(String code);
}
