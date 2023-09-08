package com.kmhai.cititzenV.Service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmhai.cititzenV.Payload.CitizenDTO;
import com.kmhai.cititzenV.Payload.Request.CitizenRequest;
import com.kmhai.cititzenV.Repository.AddressRepo;
import com.kmhai.cititzenV.Repository.CitizenRepo;
import com.kmhai.cititzenV.Repository.HamletRepo;
import com.kmhai.cititzenV.Service.CitizenService;
import com.kmhai.cititzenV.Service.UtilsService;
import com.kmhai.cititzenV.entity.Citizen;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private UtilsService utils;
    
    @Autowired
    private CitizenRepo citizenRepo;

    @Autowired
    private HamletRepo hamletRepo;

    @Autowired 
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CitizenDTO> getAll() {
        List<Citizen> citizens = citizenRepo.findAll();
        List<CitizenDTO> res = citizens.stream().map(c -> mapper.map(c, CitizenDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < res.size(); i++) {
            utils.setLocation(citizens.get(i), res.get(i));
        }
        return res;
    }

    @Override
    public CitizenDTO getByIdentity(String code) {
        Citizen citizen = citizenRepo.findByIdentityCode(code);
        CitizenDTO res = mapper.map(citizen, CitizenDTO.class);
        utils.setLocation(citizen, res);
        return res;
    }

    @Override
    public List<CitizenDTO> getByProvince(String code) {
        List<Citizen> citizens = citizenRepo.findByProvince(code);
        List<CitizenDTO> res = citizens.stream().map(c -> mapper.map(c, CitizenDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < res.size(); i++) {
            utils.setLocation(citizens.get(i), res.get(i));
        }
        return res;
    }   

    @Override
    public List<CitizenDTO> getByDistrict(String code) {
        List<Citizen> citizens = citizenRepo.findByDistrict(code);
        List<CitizenDTO> res = citizens.stream().map(c -> mapper.map(c, CitizenDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < res.size(); i++) {
            utils.setLocation(citizens.get(i), res.get(i));
        }
        return res;
    }
    
    @Override
    public List<CitizenDTO> getByWard(String code) {
        List<Citizen> citizens = citizenRepo.findByWard(code);
        List<CitizenDTO> res = citizens.stream().map(c -> mapper.map(c, CitizenDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < res.size(); i++) {
            utils.setLocation(citizens.get(i), res.get(i));
        }
        return res;
    }   

    @Override
    public List<CitizenDTO> getByHamlet(String code) {
        List<Citizen> citizens = citizenRepo.findByHamlet(code);
        List<CitizenDTO> res = citizens.stream().map(c -> mapper.map(c, CitizenDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < res.size(); i++) {
            utils.setLocation(citizens.get(i), res.get(i));
        }
        return res;
    }

    @Override
    public String createCitizen(CitizenRequest citizen, String hamletCode) {
        if (utils.checkValidCitizen(citizen, hamletCode) != "ok") return utils.checkValidCitizen(citizen, hamletCode);

        var permanent = addressRepo.findByLocationAndHamlet(citizen.getPermanentAddress().getLocation(), hamletCode);
        
        var temporary = addressRepo
                            .findByLocationAndHamlet(citizen.getTemporaryAddress().getLocation(), citizen.getTemporaryAddress().getHamletCode());
        
        var hometown = hamletRepo.findByCode(citizen.getHometownCode());

        var saveCitizen = mapper.map(citizen, Citizen.class);

        saveCitizen.setPermanent(permanent);
        saveCitizen.setTemporary(temporary);
        saveCitizen.setHometown(hometown);

        citizenRepo.save(saveCitizen);

        return "ok";
    }

    @Override
    public String deleteCitizen(String hameletCode, String idCode) {
        if (!citizenRepo.existsByIdentityCode(idCode)) {
            return "citizen doesn't exist";
        }

        Citizen citizen = citizenRepo.findByIdentityCode(idCode);
        String permanent = citizen.getPermanent().getHamlet().getCode();
        if (!hameletCode.equals(permanent)) return "you don't have permission";
        citizenRepo.delete(citizen);
        return "success";
    }

}
