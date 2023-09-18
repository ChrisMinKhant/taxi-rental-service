package com.business.taxirentalservice.service.implementation;

import com.business.taxirentalservice.constant.FuelType;
import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.constant.LicenceType;
import com.business.taxirentalservice.dto.CarDto;
import com.business.taxirentalservice.dto.CarListResponse;
import com.business.taxirentalservice.model.CNG;
import com.business.taxirentalservice.model.Car;
import com.business.taxirentalservice.model.Licence;
import com.business.taxirentalservice.repository.CarRepository;
import com.business.taxirentalservice.repository.CngRepository;
import com.business.taxirentalservice.repository.LicenceRepository;
import com.business.taxirentalservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImplementation implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LicenceRepository licenceRepository;

    @Autowired
    private CngRepository cngRepository;

    private GeneralResponse response = new GeneralResponse();

    @Override
    public String register(CarDto requestCar) {

        if (!isLicenceNumberUnique(requestCar.getLicenceNumber())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,response.LIE);
        }

        Car temporaryCar = this.fetchCar(requestCar);
        Licence temporaryLicence = this.fetchLicence(requestCar);

        licenceRepository.save(temporaryLicence);

        if (requestCar.getFuelType().equals(FuelType.CNG)) {
            CNG temporaryCng = this.fetchCng(requestCar);
            temporaryCar.setCngId(temporaryCng.getId());

            cngRepository.save(temporaryCng);
            carRepository.save(temporaryCar);

            return response.ACT;
        }

        carRepository.save(temporaryCar);
        return response.ACT;
    }

    @Override
    public List<CarListResponse> fetchCars() {
        List<Car> carList = carRepository.findAll();

        if(!carList.isEmpty()) {
            List<CarListResponse> carListResponseList = new ArrayList<>();

            for (Car car : carList) {
                carListResponseList.add(CarListResponse
                        .builder()
                        .licenceNumber(car.getLicenceNumber())
                        .build());
            }

            return carListResponseList;
        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"There is no car.");
    }

    @Override
    public CarDto fetchSingleCar(String licenceNumber) {

        if(this.isLicenceNumberUnique(licenceNumber))
        {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"There is no car with licence number "+licenceNumber);
        }

        Car temporaryCar = carRepository.findById(licenceNumber).get();
        CNG temporaryCng = cngRepository.findById(temporaryCar.getCngId()).get();
        Licence temporaryLicence = licenceRepository.findById(licenceNumber).get();

        return CarDto.builder().licenceNumber(temporaryCar.getLicenceNumber())
                .licenceDueDate(temporaryLicence.getDueDate().toString())
                .issuedRegion(temporaryLicence.getRegion())
                .model(temporaryCar.getCarModel())
                .fuelType(temporaryCar.getFuelType())
                .capacity(temporaryCng.getCapacity())
                .cngBoughtDate(temporaryCng.getBoughtDate().toString())
                .cngDueDate(temporaryCng.getDueDate().toString())
                .cngPrice(temporaryCng.getPrice())
                .carPrice(temporaryCar.getPrice())
                .build();
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
                .dueDate(LocalDate.parse(requestCar.getLicenceDueDate()))
                .region(requestCar.getIssuedRegion())
                .build();
    }

    private CNG fetchCng(CarDto requestCar) {
        return CNG.builder().id(requestCar.getCngId())
                .boughtDate(LocalDate.parse(requestCar.getCngBoughtDate()))
                .dueDate(LocalDate.parse(requestCar.getCngDueDate()))
                .capacity(requestCar.getCapacity())
                .price(requestCar.getCngPrice())
                .build();
    }

    private boolean isLicenceNumberUnique(String licenceNumber) {
        return licenceRepository.findById(licenceNumber).isEmpty() ? true : false;
    }
}
