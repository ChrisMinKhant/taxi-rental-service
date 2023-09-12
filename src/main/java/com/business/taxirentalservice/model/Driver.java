package com.business.taxirentalservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document("DRIVER")
public class Driver {
    @Id
    private String drivingLicenceNumber;
    private String name;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String drivingCarLicences;
    private int rentalPrice;
}
