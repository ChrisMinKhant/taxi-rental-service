package com.business.taxirentalservice.dto;

import com.business.taxirentalservice.constant.FuelType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarDto {
    @JsonProperty("licenceNumber")
    private String licenceNumber;
    @JsonProperty("licenceDueDate")
    private LocalDate licenceDueDate;
    @JsonProperty("issuedRegion")
    private String issuedRegion;
    @JsonProperty("model")
    private String model;
    @JsonProperty("fuelType")
    private FuelType fuelType;
    @JsonProperty("capacity")
    private double capacity;
    @JsonProperty("cngPrice")
    private int cngPrice;
    @JsonProperty("cngBoughtDate")
    private LocalDate cngBoughtDate;
    @JsonProperty("cngDueDate")
    private LocalDate cngDueDate;
    @JsonProperty("carPrice")
    private int carPrice;
}
