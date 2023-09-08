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

import com.kmhai.cititzenV.Service.DistrictService;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Secured({"ROLE_A1"})
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(districtService.getAll(), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2", "ROLE_A3"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable("code") String code) {
        return new ResponseEntity<>(districtService.getByCode(code), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2", "ROLE_A3"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/by-province/{province-code}")
    public ResponseEntity<?> getByProvinceCode(@PathVariable("province-code") String code) {
        return new ResponseEntity<>(districtService.getByProvinceCode(code), HttpStatus.OK);
    }
}
