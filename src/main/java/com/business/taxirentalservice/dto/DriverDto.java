package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverDto {
    @JsonProperty("drivingLicenceNumber")
    private String drivingLicenceNumber;
    @JsonProperty("licenceDueDate")
    private LocalDate licenceDueDate;
    @JsonProperty("issuedRegion")
    private String issuedRegion;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;
    @JsonProperty("drivingCarLicenceNumber")
    private String drivingCarLicenceNumber;
    @JsonProperty("rentalPrice")
    private int rentalPrice;
}
