package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.dto.DriverDto;
import com.business.taxirentalservice.dto.DriverListResponse;
import com.business.taxirentalservice.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Driver", tags = "Driver Endpoints")
public class DriverController {
    @Autowired
    private DriverService driverService;

    private final Logger logger = LogManager.getLogger(DriverController.class);

    private final GeneralResponse response = new GeneralResponse();

    @PostMapping(value = "/drivers", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register New Drivers")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody DriverDto driverDto) {
        logger.info("fetch requested driver >>> {}", driverDto);

        String result = driverService.register(driverDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/drivers")
    @ApiOperation(value = "Fetch All Drivers")
    public ResponseEntity<?> fetchDriverInformation() {
        List<DriverListResponse> driverList = driverService.fetchDrivers();

        return new ResponseEntity<>(driverList, HttpStatus.OK);
    }

    @GetMapping(value = "/drivers/{licenceNumber}")
    @ApiOperation(value = "Fetch Single Driver")
    public ResponseEntity<?> fetchSingleDriverByLicenceNumber(@PathVariable("licenceNumber") @NotBlank(message = "licence number must not be empty." ) String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        DriverDto driverResponse = driverService.fetchSingleDriver(licenceNumber);

        return new ResponseEntity<>(driverResponse, HttpStatus.OK);
    }
}
