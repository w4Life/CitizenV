package com.kmhai.cititzenV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmhai.cititzenV.Service.HamletService;

@RestController
@RequestMapping("/hamlets")
public class HamletController {
    
    @Autowired
    private HamletService hamletService;

    @Secured({"ROLE_A1"})
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(hamletService.getAll(), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2", "ROLE_A3", "ROLE_B1", "ROLE_B2"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable("code") String code) {
        return new ResponseEntity<>(hamletService.getByCode(code), HttpStatus.OK);
    }
    
    @Secured({"ROLE_A1", "ROLE_A2", "ROLE_A3"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/by-district/{district-code}")
    public ResponseEntity<?> getByDistrict(@PathVariable("district-code") String code) {
        return new ResponseEntity<>(hamletService.getByDistrictCode(code), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/by-province/{province-code}")
    public ResponseEntity<?> getByProvince(@PathVariable("province-code") String code) {
        return new ResponseEntity<>(hamletService.getByProvinceCode(code), HttpStatus.OK);
    }

    @Secured({"ROlE_A1", "ROLE_A2", "ROLE_A3", "ROLE_B1"})
    @GetMapping("/by-ward/{ward-code}")
    public ResponseEntity<?> getByWard(@PathVariable("ward-code") String code) {
        return new ResponseEntity<>(hamletService.getByWardCode(code), HttpStatus.OK);
    }
}
