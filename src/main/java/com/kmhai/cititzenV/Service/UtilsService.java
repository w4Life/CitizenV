package com.kmhai.cititzenV.Service;

import org.springframework.security.core.Authentication;

import com.kmhai.cititzenV.Payload.CitizenDTO;
import com.kmhai.cititzenV.Payload.Request.CitizenRequest;
import com.kmhai.cititzenV.Payload.Request.UserRequest;
import com.kmhai.cititzenV.entity.Citizen;

public interface UtilsService {
    String locationToString(String code);
    void setLocation(Citizen c, CitizenDTO cDTO);
    String checkValidCitizen(CitizenRequest c, String hamletCode);
    String checkValidUser(UserRequest u, String divisionCode);
    String checkValidUserByA1(UserRequest u);
    boolean checkAccess(Authentication auth, String code);
    boolean checkUserPermission(Authentication auth, String code);
}
