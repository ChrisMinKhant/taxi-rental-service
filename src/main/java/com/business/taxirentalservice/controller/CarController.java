package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.dto.CarListResponse;
import com.business.taxirentalservice.service.CarService;
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
@Api(value = "Car", tags = "Car Controller")
public class CarController {
    @Autowired
    private CarService carService;

    private Logger logger = LogManager.getLogger(CarController.class);

    private final GeneralResponse response = new GeneralResponse();

    @PostMapping(value = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register New Cars")
    public ResponseEntity<?> registerCar(@Valid @RequestBody CarDto carDto) {

        logger.info("fetch requested car >>> {}", carDto);

        String isRegistered = carService.register(carDto);

        return new ResponseEntity<>(isRegistered, HttpStatus.OK);
    }

    @GetMapping(value = "/cars")
    @ApiOperation(value = "Fetch All Cars")
    public ResponseEntity<?> fetchCarInformation()
    {
        List<CarListResponse> carList = carService.fetchCars();

        return new ResponseEntity<>(carList,HttpStatus.OK);
    }

    @GetMapping(value = "/cars/{licenceNumber}")
    @ApiOperation(value = "Fetch Single Car")
    public ResponseEntity<?> fetchSingleCarByLicenceNumber(@PathVariable("licenceNumber") @NotBlank(message = "licence number must not be empty.") String licenceNumber)
    {
        logger.info("fetch requested licence number >>> {}",licenceNumber);

        CarDto carResponse = carService.fetchSingleCar(licenceNumber);

        return new ResponseEntity<>(carResponse,HttpStatus.OK);
    }
}
