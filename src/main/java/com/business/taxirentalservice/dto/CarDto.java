package com.business.taxirentalservice.dto;

import com.business.taxirentalservice.constant.FuelType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CarDto {
    @NotEmpty(message = "Licence Number Must Not Empty")
    @Pattern(regexp = "[1-9]{1}[A-Z]{1}[0-9]{4}", message = "Licence Number is in invalid form.")
    @JsonProperty("licenceNumber")
    private String licenceNumber;

    @NotNull(message = "Licence Due Date Must Not Empty")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Licence Due Date is in invalid form.")
    @JsonProperty("licenceDueDate")
    private String licenceDueDate;

    @NotEmpty(message = "Issued Region Must Not Empty")
    @JsonProperty("issuedRegion")
    private String issuedRegion;

    @NotEmpty(message = "Model Must Not Empty")
    @JsonProperty("model")
    private String model;

    @NotNull(message = "Fuel Type Must Not Empty")
    @JsonProperty("fuelType")
    private FuelType fuelType;

    @JsonProperty("capacity")
    private double capacity;

    @JsonProperty("cngPrice")
    private int cngPrice;

    @JsonProperty("cngBoughtDate")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "CNG Bought Date is in invalid form.")
    private String cngBoughtDate;

    @JsonProperty("cngDueDate")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "CNG Due Date is in invalid form.")
    private String cngDueDate;

    @NotNull(message = "Car Price Must Not Empty")
    @JsonProperty("carPrice")
    private int carPrice;
}
