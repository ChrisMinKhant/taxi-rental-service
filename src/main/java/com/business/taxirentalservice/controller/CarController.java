package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class CarController {
    @Autowired
    private CarService carService;

    private Logger logger = LogManager.getLogger(CarController.class);

    @PostMapping(value = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerCar(@Valid @RequestBody CarDto carRequest) {

        logger.info("Fetch Request >>> {}",carRequest);

        String isRegistered = carService.register(carRequest);

        return new ResponseEntity<>(isRegistered, HttpStatus.OK);
    }
}
