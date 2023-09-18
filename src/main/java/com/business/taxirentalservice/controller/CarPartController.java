package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.dto.CarPartDto;
import com.business.taxirentalservice.dto.DecreaseCarPartDto;
import com.business.taxirentalservice.service.CarPartInventoryService;
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
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Car Part", tags = "Car Part Controller")
public class CarPartController {
    @Autowired
    private CarPartInventoryService carPartInventoryService;

    private final Logger logger = LogManager.getLogger(CarPartController.class);

    @PostMapping(value = "/inventories", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register Car Part")
    public ResponseEntity<?> registerCarPart(@Valid @RequestBody CarPartDto carPartDto)
    {
        logger.info("fetch requested car part >>> {}",carPartDto);

        String result = carPartInventoryService.registerCarPart(carPartDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/inventories/decrease", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Decrease Car Part")
    public ResponseEntity<?> decreaseCarPart(@Valid @RequestBody DecreaseCarPartDto decreaseCarPartDto)
    {
        logger.info("fetch decrease car part request >>> {}",decreaseCarPartDto);

        String result = carPartInventoryService.decreaseCarPart(decreaseCarPartDto);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/inventories")
    @ApiOperation(value = "Fetch All Car Parts")
    public ResponseEntity<?> fetchCarPartList()
    {
        List<CarPartDto> carPartDtoList = carPartInventoryService.fetchCarPartList();

        return new ResponseEntity<>(carPartDtoList,HttpStatus.OK);
    }
}
