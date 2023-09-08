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

import com.kmhai.cititzenV.Service.ProvinceService;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @Secured({"ROLE_A1"})
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(provinceService.getAll(), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/{province-code}")
    public ResponseEntity<?> getByCode(@PathVariable("province-code") String code) {
        return new ResponseEntity<>(provinceService.getByCode(code), HttpStatus.OK);
    } 
}
