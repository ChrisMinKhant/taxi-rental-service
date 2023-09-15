package com.business.taxirentalservice.model;

import com.business.taxirentalservice.constant.FuelType;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("CAR")
public class Car {
    @Id
    private String licenceNumber;
    private String carModel;
    private FuelType fuelType;
    private String cngId;
    private int price;
}
