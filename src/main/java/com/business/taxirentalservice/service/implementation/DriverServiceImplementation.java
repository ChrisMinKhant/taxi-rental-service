package com.business.taxirentalservice.service.implementation;

import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.constant.LicenceType;
import com.business.taxirentalservice.dto.DriverDto;
import com.business.taxirentalservice.dto.DriverListResponse;
import com.business.taxirentalservice.model.Driver;
import com.business.taxirentalservice.model.Licence;
import com.business.taxirentalservice.repository.DriverRepository;
import com.business.taxirentalservice.repository.LicenceRepository;
import com.business.taxirentalservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverServiceImplementation implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LicenceRepository licenceRepository;

    private GeneralResponse response = new GeneralResponse();

    @Override
    public String register(DriverDto driverDto) {

        if (!isLicenceNumberUnique(driverDto.getDriverLicence())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,response.LIE);
        }

        if (isCarInUse(driverDto.getDrivingCarLicenceNumber())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,response.CIU);
        }

        Driver driver = Driver.builder()
                .licenceNumber(driverDto.getDriverLicence())
                .name(driverDto.getName())
                .address(driverDto.getAddress())
                .phoneNumber(driverDto.getPhone())
                .dateOfBirth(LocalDate.parse(driverDto.getDateOfBirth()))
                .drivingCarLicence(driverDto.getDrivingCarLicenceNumber())
                .rentalPrice(driverDto.getRentalPrice())
                .build();

        Licence licence = this.fetchLicence(driverDto);

        driverRepository.save(driver);
        licenceRepository.save(licence);

        return response.ACT;
    }

    @Override
    public List<DriverListResponse> fetchDrivers() {
        List<Driver> driverList = driverRepository.findAll();

        if(!driverList.isEmpty()) {
            List<DriverListResponse> driverListResponseList = new ArrayList<>();

            for (Driver driver : driverList) {
                driverListResponseList.add(DriverListResponse
                        .builder()
                        .licenceNumber(driver.getLicenceNumber())
                        .build());
            }

            return driverListResponseList;
        }

        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"There is no driver.");
    }

    @Override
    public DriverDto fetchSingleDriver(String licenceNumber) {
        if (this.isLicenceNumberUnique(licenceNumber)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"There is no driver with licence number "+licenceNumber);
        }

        Driver temporaryDriver = driverRepository.findById(licenceNumber).get();
        Licence temporaryLicence = licenceRepository.findById(licenceNumber).get();

        return DriverDto.builder().driverLicence(temporaryDriver.getLicenceNumber())
                .licenceDueDate(temporaryLicence.getDueDate().toString())
                .issuedRegion(temporaryLicence.getRegion())
                .name(temporaryDriver.getName())
                .address(temporaryDriver.getAddress())
                .phone(temporaryDriver.getPhoneNumber())
                .dateOfBirth(temporaryDriver.getDateOfBirth().toString())
                .drivingCarLicenceNumber(temporaryDriver.getDrivingCarLicence())
                .rentalPrice(temporaryDriver.getRentalPrice())
                .build();
    }

    @Override
    public int fetchExpectedRentalPrice(String drivingLicenceNumber) {
        if (!driverRepository.findById(drivingLicenceNumber).isEmpty()) {
            return driverRepository.findById(drivingLicenceNumber).get().getRentalPrice();
        }
        return 0;
    }

    @Override
    public Driver fetchDriver(String drivingCarLicenceNumber) {
        if (!driverRepository.findById(drivingCarLicenceNumber).isEmpty()) {
            return driverRepository.findById(drivingCarLicenceNumber).get();
        }
        return null;
    }

    private Licence fetchLicence(DriverDto requestDriver) {
        return Licence.builder().licenceNumber(requestDriver.getDriverLicence())
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
