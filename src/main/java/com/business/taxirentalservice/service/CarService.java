package com.business.taxirentalservice.service;

import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.dto.CarListResponse;

import java.util.List;

public interface CarService {
    String register(CarDto requestCar);
    List<CarListResponse> fetchCars();
    CarDto fetchSingleCar(String licenceNumber);
}
