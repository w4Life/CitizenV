package com.kmhai.cititzenV.Service;

import java.util.List;

import com.kmhai.cititzenV.Payload.HamletDTO;

public interface HamletService {
    List<HamletDTO> getAll();
    HamletDTO getByCode(String code);
    List<HamletDTO> getByDistrictCode(String code);
    List<HamletDTO> getByProvinceCode(String code);
    List<HamletDTO> getByWardCode(String code);
}
