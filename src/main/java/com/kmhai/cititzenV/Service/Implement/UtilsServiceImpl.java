package com.kmhai.cititzenV.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Payload.CitizenDTO;
import com.kmhai.cititzenV.Payload.Request.CitizenRequest;
import com.kmhai.cititzenV.Payload.Request.UserRequest;
import com.kmhai.cititzenV.Repository.AddressRepo;
import com.kmhai.cititzenV.Repository.CitizenRepo;
import com.kmhai.cititzenV.Repository.DistrictRepo;
import com.kmhai.cititzenV.Repository.HamletRepo;
import com.kmhai.cititzenV.Repository.ProvinceRepo;
import com.kmhai.cititzenV.Repository.RoleRepo;
import com.kmhai.cititzenV.Repository.UserRepo;
import com.kmhai.cititzenV.Repository.WardRepo;
import com.kmhai.cititzenV.Service.UtilsService;
import com.kmhai.cititzenV.entity.Citizen;
import com.kmhai.cititzenV.entity.District;
import com.kmhai.cititzenV.entity.Hamlet;
import com.kmhai.cititzenV.entity.Province;
import com.kmhai.cititzenV.entity.Ward;

@Service
public class UtilsServiceImpl implements UtilsService {
    
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private HamletRepo hamletRepo;

    @Autowired
    private WardRepo wardRepo;

    @Autowired
    private DistrictRepo districtRepo;

    @Autowired
    private ProvinceRepo provinceRepo;

    @Autowired
    private CitizenRepo citizenRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public String locationToString(String code) {
        Hamlet hamlet = hamletRepo.findByCode(code);
        Ward ward = wardRepo.findByCode(code.substring(0, 6));
        District district = districtRepo.findByCode(code.substring(0, 4));
        Province province = provinceRepo.findByCode(code.substring(0, 2));

        return hamlet.getName() + ", " + ward.getName() + ", " + district.getName() + ", " + province.getName();
    }

    @Override
    public void setLocation(Citizen c, CitizenDTO cDTO) {
        String hometownCode = c.getHometown().getCode();
        String permanentCode = c.getPermanent().getHamlet().getCode();
        String temporaryCode = c.getTemporary().getHamlet().getCode();

        String permanent = c.getPermanent().getLocation();
        String temporary = c.getTemporary().getLocation();

        cDTO.setHomeTown(locationToString(hometownCode));
        cDTO.setPermanent(permanent + ", " + locationToString(permanentCode));
        cDTO.setTemporary(temporary + ", " + locationToString(temporaryCode));
    }

    @Override
    public String checkValidCitizen(CitizenRequest c, String hamletCode) {
        String res = "";

        if (citizenRepo.existsByIdentityCode(c.getIdentityCode())) res += "Invalid indentity Code\n";
        
        if (c.getPermanentAddress().getHamletCode() != hamletCode) {
            res += "invalid permanent address\n";
        } else if (addressRepo.existsByLocationAdnHamlet(c.getPermanentAddress().getLocation(), c.getPermanentAddress().getHamletCode()) == 0) {
            res += "invalid permanent address\n";                
        }

        if (!hamletRepo.existsByCode(c.getTemporaryAddress().getHamletCode())) {
            res += "invalid temporary address\n";
        } else if (addressRepo.existsByLocationAdnHamlet(c.getTemporaryAddress().getLocation(), c.getPermanentAddress().getHamletCode()) == 0) {
            res += "invalid temporary address\n";
        }

        if (!hamletRepo.existsByCode(c.getHometownCode())) res += "invalid hometown\n";

        return (res.length() > 0) ? res : "ok";
    }

    @Override
    public String checkValidUser(UserRequest user, String divisionCode) {
        String username = user.getUsername();
        switch (divisionCode.length()) {
            case 2:
                if (username.length() != 4 || !username.substring(0, 2).equals(divisionCode)) return "invalid";
                break;
            case 4:
                if (username.length() != 6 || !username.substring(0, 4).equals(divisionCode)) return "invalid";
                break;
            case 6:
                if (username.length() != 8 || !username.substring(0, 6).equals(divisionCode)) return "invalid";
                break;
        }

        if (userRepo.existsByUsername(user.getUsername())) {
            return "invalid";
        } 

        if (!roleRepo.existsByRole(user.getRole())) {
            return "invalid";
        } else {
            switch (divisionCode.length()) {
                case 2:
                    if (!user.getRole().equals("ROLE_A3")) return "invalid";
                    break;
                case 4:
                    if (!user.getRole().equals("ROLE_B1")) return "invalid";
                    break;
                case 6:
                    if (!user.getRole().equals("ROLE_B2")) return "invalid";
                    break;
            }
        }

        return "ok";
    }

    @Override
    public String checkValidUserByA1(UserRequest u) {
        
        if (u.getUsername().length() != 2) return "invalid";

        if (userRepo.existsByUsername(u.getUsername())) {
            return "invalid";
        } 

        if (!roleRepo.existsByRole(u.getRole())) {
            return "invalid";
        } else if (!u.getRole().equals("ROLE_A2")) return "invalid";

        return "ok";
    }

    @Override
    public boolean checkAccess(Authentication auth, String code) {
        String username = auth.getName();
        if (auth.getAuthorities().contains("ROLE_A1")) return true;
        switch (username.length()) {
            case 2:
                if (code.length() >= 2 && code.substring(0, 2).equals(username)) return true; 
                break;
            case 4:
                if (code.length() >= 4 && code.substring(0, 4).equals(username)) return true;
                break;
            case 6:
                if (code.length() >= 6 && code.substring(0, 6).equals(username)) return true;
            default:
                break;
        }
        return username.equals(code);
    }

    @Override
    public boolean checkUserPermission(Authentication auth, String divisionCode) {
        return auth.getName().equals(divisionCode);
    }


}
