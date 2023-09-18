package com.business.taxirentalservice.service.implementation;

import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.dto.CarPartDto;
import com.business.taxirentalservice.dto.DecreaseCarPartDto;
import com.business.taxirentalservice.model.CarPartInventory;
import com.business.taxirentalservice.repository.CarPartInventoryRepository;
import com.business.taxirentalservice.service.CarPartInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CarPartInventoryServiceImplementation implements CarPartInventoryService {
    @Autowired
    private CarPartInventoryRepository carPartInventoryRepository;

    private final GeneralResponse response = new GeneralResponse();

    @Override
    public String registerCarPart(CarPartDto carPartDto) {

        if (!isCarPartExist(carPartDto.getCarPartName())) {

            carPartInventoryRepository.save(CarPartInventory.builder()
                    .id(UUID.randomUUID().toString())
                    .carPartName(carPartDto.getCarPartName())
                    .quantity(carPartDto.getQuantity())
                    .totalPrice(carPartDto.getPrice() * carPartDto.getQuantity())
                    .build());

            return response.ACT;
        }

        CarPartInventory carPartInventory = carPartInventoryRepository.findByCarPartName(carPartDto.getCarPartName()).get();

        carPartInventory.setQuantity(carPartInventory.getQuantity() + carPartDto.getQuantity());

        carPartInventory.setTotalPrice(carPartInventory.getTotalPrice() + (carPartDto.getPrice() * carPartDto.getQuantity()));

        carPartInventoryRepository.save(carPartInventory);

        return response.ACT;
    }

    @Override
    public String decreaseCarPart(DecreaseCarPartDto decreaseCarPartDto) {
        if (!isCarPartExist(decreaseCarPartDto.getCarPartName())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Car Part Not Found");
        }

        if (carPartInventoryRepository.findByCarPartName(decreaseCarPartDto.getCarPartName()).get().getQuantity() <= 0 ||
                carPartInventoryRepository.findByCarPartName(decreaseCarPartDto.getCarPartName()).get().getQuantity() < decreaseCarPartDto.getQuantity()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Do not have enough car part remaining.");
        }

        CarPartInventory carPartInventory = carPartInventoryRepository.findByCarPartName(decreaseCarPartDto.getCarPartName()).get();

        carPartInventory.setTotalPrice(carPartInventory.getTotalPrice() -
                ((carPartInventory.getTotalPrice() / carPartInventory.getQuantity()) * decreaseCarPartDto.getQuantity()));

        carPartInventory.setQuantity(carPartInventory.getQuantity() - decreaseCarPartDto.getQuantity());

        carPartInventoryRepository.save(carPartInventory);

        return response.ACT;
    }

    @Override
    public List<CarPartDto> fetchCarPartList() {
        List<CarPartInventory> carPartInventoryList = carPartInventoryRepository.findAll();

        if (!carPartInventoryList.isEmpty()) {
            List<CarPartDto> carPartDtoList = new ArrayList<>();

            for (CarPartInventory carPartInventory : carPartInventoryList) {
                carPartDtoList.add(CarPartDto.builder()
                        .carPartName(carPartInventory.getCarPartName())
                        .quantity(carPartInventory.getQuantity())
                        .price(carPartInventory.getTotalPrice())
                        .build());
            }

            return carPartDtoList;
        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "there is no car part.");
    }

    private boolean isCarPartExist(String carPartName) {
        return !carPartInventoryRepository.findByCarPartName(carPartName).isEmpty() ? true : false;
    }
}
