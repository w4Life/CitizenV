package com.kmhai.cititzenV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmhai.cititzenV.Service.WardService;

@RestController
@RequestMapping("/wards")
public class WardController {
    
    @Autowired
    private WardService wardService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(wardService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable("code") String code) {
        return new ResponseEntity<>(wardService.getByCode(code), HttpStatus.OK);
    }
    
    @GetMapping("/by-district/{district-code}")
    public ResponseEntity<?> getByDistrict(@PathVariable("district-code") String code) {
        return new ResponseEntity<>(wardService.getByDistrictCode(code), HttpStatus.OK);
    }

    @GetMapping("/by-province/{province-code}")
    public ResponseEntity<?> getByProvince(@PathVariable("province-code") String code) {
        return new ResponseEntity<>(wardService.getByProvinceCode(code), HttpStatus.OK);
    }
}
