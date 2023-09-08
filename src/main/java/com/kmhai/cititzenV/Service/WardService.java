package com.kmhai.cititzenV.Service;

import java.util.List;

import com.kmhai.cititzenV.Payload.WardDTO;

public interface WardService {
    List<WardDTO> getAll();
    WardDTO getByCode(String code);
    List<WardDTO> getByDistrictCode(String code);
    List<WardDTO> getByProvinceCode(String code);
}
