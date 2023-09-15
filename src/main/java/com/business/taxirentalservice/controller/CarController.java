package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.dto.CarListResponse;
import com.business.taxirentalservice.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class CarController {
    @Autowired
    private CarService carService;

    private Logger logger = LogManager.getLogger(CarController.class);

    @PostMapping(value = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerCar(@Valid @RequestBody CarDto carDto) {

        logger.info("Fetch Request >>> {}", carDto);

        String isRegistered = carService.register(carDto);

        return new ResponseEntity<>(isRegistered, HttpStatus.OK);
    }

    @GetMapping(value = "/cars")
    public ResponseEntity<?> fetchCarInformation()
    {
        List<CarListResponse> carList = carService.fetchCars();

        return new ResponseEntity<>(carList,HttpStatus.OK);
    }

    @GetMapping(value = "/cars/{licenceNumber}")
    public ResponseEntity<?> fetchSingleCarByLicenceNumber(@PathVariable("licenceNumber") String licenceNumber)
    {
        CarDto carResponse = carService.fetchSingleCar(licenceNumber);

        return new ResponseEntity<>(carResponse,HttpStatus.OK);
    }
}
