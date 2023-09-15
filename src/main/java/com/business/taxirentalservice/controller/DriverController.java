package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.dto.CarListResponse;
import com.business.taxirentalservice.dto.DriverDto;
import com.business.taxirentalservice.dto.DriverListResponse;
import com.business.taxirentalservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/drivers")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody DriverDto driverDto) {

        String result = driverService.register(driverDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/drivers")
    public ResponseEntity<?> fetchDriverInformation()
    {
        List<DriverListResponse> driverList = driverService.fetchDrivers();

        return new ResponseEntity<>(driverList,HttpStatus.OK);
    }

    @GetMapping(value = "/drivers/{licenceNumber}")
    public ResponseEntity<?> fetchSingleDriverByLicenceNumber(@PathVariable("licenceNumber") String licenceNumber)
    {
        DriverDto driverResponse = driverService.fetchSingleDriver(licenceNumber);

        return new ResponseEntity<>(driverResponse,HttpStatus.OK);
    }
}
