package com.business.taxirentalservice.service.implementation;

import com.business.taxirentalservice.dto.DriverDto;
import com.business.taxirentalservice.repository.DriverRepository;
import com.business.taxirentalservice.repository.LicenceRepository;
import com.business.taxirentalservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImplementation implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LicenceRepository licenceRepository;

    @Override
    public String register(DriverDto driverRequest) {
        return null;
    }
}
