package com.kmhai.cititzenV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmhai.cititzenV.Payload.Request.UserRequest;
import com.kmhai.cititzenV.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Secured({"ROLE_A2", "ROLE_A3", "ROLE_B1"})
    @PreAuthorize("@utilsServiceImpl.checkUserPermission(authentication, #code)")
    @PostMapping("/{division-code}")
    public ResponseEntity<?> createUser(@RequestBody UserRequest user, @PathVariable("division-code") String code) {
        return new ResponseEntity<>(userService.createUser(user, code), HttpStatus.OK);
    }

    @Secured({"ROLE_A1"})
    @PostMapping("/A1")
    public ResponseEntity<?> createUserByA1(@RequestBody UserRequest user) {
        return new ResponseEntity<>(userService.createUserByA1(user), HttpStatus.OK);
    }
}
