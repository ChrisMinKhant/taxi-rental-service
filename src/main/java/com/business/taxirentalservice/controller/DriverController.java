package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.dto.DriverDto;
import com.business.taxirentalservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/drivers")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody DriverDto driverRequest){

        String result = driverService.register(driverRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
