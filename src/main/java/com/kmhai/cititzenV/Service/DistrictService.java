package com.kmhai.cititzenV.Service;

import java.util.List;

import com.kmhai.cititzenV.Payload.DistrictDTO;

public interface DistrictService {
    
    List<DistrictDTO> getAll();
    DistrictDTO getByCode(String code);
    List<DistrictDTO> getByProvinceCode(String code);
}
