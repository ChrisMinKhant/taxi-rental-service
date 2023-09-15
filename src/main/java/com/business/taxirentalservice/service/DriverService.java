package com.business.taxirentalservice.service;

import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.dto.DriverDto;
import com.business.taxirentalservice.dto.DriverListResponse;
import com.business.taxirentalservice.model.Driver;

import java.util.List;

public interface DriverService {
    String register(DriverDto driverDto);
    List<DriverListResponse> fetchDrivers();
    DriverDto fetchSingleDriver(String licenceNumber);
    int fetchExpectedRentalPrice(String name);
    Driver fetchDriver(String licenceNumber);
}
