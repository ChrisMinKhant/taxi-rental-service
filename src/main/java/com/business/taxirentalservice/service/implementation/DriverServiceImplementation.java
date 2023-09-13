package com.business.taxirentalservice.service.implementation;

import com.business.taxirentalservice.constant.LicenceType;
import com.business.taxirentalservice.constant.RegisterResponse;
import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.dto.DriverDto;
import com.business.taxirentalservice.model.Driver;
import com.business.taxirentalservice.model.Licence;
import com.business.taxirentalservice.repository.DriverRepository;
import com.business.taxirentalservice.repository.LicenceRepository;
import com.business.taxirentalservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DriverServiceImplementation implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LicenceRepository licenceRepository;

    private RegisterResponse response = new RegisterResponse();

    @Override
    public String register(DriverDto driverRequest) {

        if (!isLicenceNumberUnique(driverRequest.getDrivingLicenceNumber())) {
            return response.LICENCEEXIST;
        }

        if (isCarInUse(driverRequest.getDrivingCarLicenceNumber())) {
            return response.CARINUSE;
        }

        Driver driver = Driver.builder()
                .drivingLicenceNumber(driverRequest.getDrivingLicenceNumber())
                .name(driverRequest.getName())
                .address(driverRequest.getAddress())
                .phoneNumber(driverRequest.getPhone())
                .dateOfBirth(LocalDate.parse(driverRequest.getDateOfBirth()))
                .drivingCarLicence(driverRequest.getDrivingCarLicenceNumber())
                .rentalPrice(driverRequest.getRentalPrice())
                .build();

        Licence licence = this.fetchLicence(driverRequest);

        driverRepository.save(driver);
        licenceRepository.save(licence);

        return response.ACCEPT;
    }

    private Licence fetchLicence(DriverDto requestDriver) {
        return Licence.builder().licenceNumber(requestDriver.getDrivingLicenceNumber())
                .type(LicenceType.DRIVER)
                .dueDate(LocalDate.parse(requestDriver.getLicenceDueDate()))
                .region(requestDriver.getIssuedRegion())
                .build();
    }

    private boolean isLicenceNumberUnique(String licenceNumber) {
        return licenceRepository.findById(licenceNumber).isEmpty() ? true : false;
    }

    private boolean isCarInUse(String drivingCarLicenceNumber) {
        return !driverRepository.findByDrivingCarLicenceNumber(drivingCarLicenceNumber).isEmpty() ? true : false;
    }
}
