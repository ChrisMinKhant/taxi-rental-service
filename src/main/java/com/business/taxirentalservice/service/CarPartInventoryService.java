package com.business.taxirentalservice.service;

import com.business.taxirentalservice.dto.CarPartDto;
import com.business.taxirentalservice.dto.DecreaseCarPartDto;
import com.business.taxirentalservice.model.CarPartInventory;

import java.util.List;
import java.util.Optional;

public interface CarPartInventoryService {
    String registerCarPart(CarPartDto carPartDto);
    String decreaseCarPart(DecreaseCarPartDto decreaseCarPartDto);

    List<CarPartDto> fetchCarPartList();
}
