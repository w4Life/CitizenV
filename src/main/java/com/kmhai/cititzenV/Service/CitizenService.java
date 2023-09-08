package com.kmhai.cititzenV.Service;

import java.util.List;

import com.kmhai.cititzenV.Payload.CitizenDTO;
import com.kmhai.cititzenV.Payload.Request.CitizenRequest;

public interface CitizenService {
    
    List<CitizenDTO> getAll();
    CitizenDTO getByIdentity(String code);
    List<CitizenDTO> getByProvince(String code);
    List<CitizenDTO> getByDistrict(String code);
    List<CitizenDTO> getByWard(String code);
    List<CitizenDTO> getByHamlet(String code);
    String createCitizen(CitizenRequest citizen, String hamletCode);
    //String updateCitizen(CitizenRequest citizen);
    String deleteCitizen(String hamletCode, String idCode);
}
