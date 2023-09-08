package com.kmhai.cititzenV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmhai.cititzenV.Payload.Request.CitizenRequest;
import com.kmhai.cititzenV.Payload.Request.DeleteRequest;
import com.kmhai.cititzenV.Service.CitizenService;

@RestController
@RequestMapping("/citizens")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;
    
    @Secured({"ROLE_A1"})
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(citizenService.getAll(), HttpStatus.OK);
    }

    @Secured({"ROLE_A1"})
    @GetMapping("/{identity}")
    public ResponseEntity<?> getByIdentity(@PathVariable("identity") String code) {
        return new ResponseEntity<>(citizenService.getByIdentity(code), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/by-province/{province-code}")
    public ResponseEntity<?> getByProvince(@PathVariable("province-code") String code) {
        return new ResponseEntity<>(citizenService.getByProvince(code), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2", "ROLE_A3"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/by-district/{district-code}")
    public ResponseEntity<?> getByDistrict(@PathVariable("district-code") String code) {
        return new ResponseEntity<>(citizenService.getByDistrict(code), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2", "ROLE_A3, ROLE_B1"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/by-ward/{ward-code}")
    public ResponseEntity<?> getByWard(@PathVariable("ward-code") String code) {
        return new ResponseEntity<>(citizenService.getByWard(code), HttpStatus.OK);
    }

    @Secured({"ROLE_A1", "ROLE_A2", "ROLE_A3, ROLE_B1, ROLE_B2"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #code)")
    @GetMapping("/by-hamlet/{hamlet-code}")
    public ResponseEntity<?> getByHamlet(@PathVariable("hamlet-code") String code) {
        return new ResponseEntity<>(citizenService.getByHamlet(code), HttpStatus.OK);
    }

    @Secured({"ROLE_B2"})
    @PostMapping("/{hamlet-code}")
    public ResponseEntity<?> createCitizen(@RequestBody CitizenRequest citizen, @PathVariable("hamlet-code") String hamletCode) {
        return new ResponseEntity<>(citizenService.createCitizen(citizen, hamletCode), HttpStatus.OK);
    }

    @Secured({"ROLE_B2"})
    @PreAuthorize("@utilsServiceImpl.checkAccess(authentication, #hamletCode)")
    @DeleteMapping("/{hamlet-code}")
    public ResponseEntity<?> deleteCitizen(@RequestBody DeleteRequest req, @PathVariable("hamlet-code") String hamletcode) {
        var res = citizenService.deleteCitizen(hamletcode, req.getIdentityCode());
        if (res == "you don't have permission") return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
