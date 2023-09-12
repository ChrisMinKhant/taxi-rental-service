package com.business.taxirentalservice.service.implementation;

import com.business.taxirentalservice.constant.FuelType;
import com.business.taxirentalservice.constant.LicenceType;
import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.model.CNG;
import com.business.taxirentalservice.model.Car;
import com.business.taxirentalservice.model.Licence;
import com.business.taxirentalservice.repository.CarRepository;
import com.business.taxirentalservice.repository.CngRepository;
import com.business.taxirentalservice.repository.LicenceRepository;
import com.business.taxirentalservice.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarServiceImplementation implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LicenceRepository licenceRepository;

    @Autowired
    private CngRepository cngRepository;

    private Logger logger = LogManager.getLogger(CarServiceImplementation.class);

    @Override
    public String register(CarDto requestCar) {
        Car temporaryCar = this.fetchCar(requestCar);
        Licence temporaryLicence = this.fetchLicence(requestCar);

        carRepository.save(temporaryCar);
        licenceRepository.save(temporaryLicence);

        if (requestCar.getFuelType().equals(FuelType.CNG)) {
            CNG temporaryCng = this.fetchCng(requestCar);
            cngRepository.save(temporaryCng);
        }

        return "Car Registeration Completed";
    }

    private Car fetchCar(CarDto requestCar) {
        return Car.builder().licenceNumber(requestCar.getLicenceNumber())
                .carModel(requestCar.getModel())
                .fuelType(requestCar.getFuelType())
                .price(requestCar.getCarPrice())
                .build();
    }

    private Licence fetchLicence(CarDto requestCar) {
        return Licence.builder().licenceNumber(requestCar.getLicenceNumber())
                .type(LicenceType.CAR)
                .dueDate(requestCar.getLicenceDueDate())
                .region(requestCar.getIssuedRegion())
                .build();
    }

    private CNG fetchCng(CarDto requestCar) {
        return CNG.builder().id(UUID.randomUUID().toString())
                .boughtDate(requestCar.getCngBoughtDate())
                .dueDate(requestCar.getCngDueDate())
                .capacity(requestCar.getCapacity())
                .price(requestCar.getCngPrice())
                .build();
    }
}
