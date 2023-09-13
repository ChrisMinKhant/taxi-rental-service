package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DriverDto {
    @NotEmpty(message = "Driving Licence Number Must Not Empty.")
    @Pattern(regexp = "[A-Z]{1}/[0-9]{5}/[0-9]{2}", message = "Driving Licence Number is in invalid form.")
    @JsonProperty("drivingLicenceNumber")
    private String drivingLicenceNumber;

    @NotEmpty(message = "Licence Due Date Must Not Empty.")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Licence Due Date is in invalid form.")
    @JsonProperty("licenceDueDate")
    private String licenceDueDate;

    @NotEmpty(message = "Issued Region Must Not Empty.")
    @JsonProperty("issuedRegion")
    private String issuedRegion;

    @NotEmpty(message = "Name Must Not Empty.")
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "Address Must Not Empty.")
    @JsonProperty("address")
    private String address;

    @NotEmpty(message = "Phone Must Not Empty.")
    @JsonProperty("phone")
    private String phone;

    @NotEmpty(message = "Date of Birth Must Not Empty.")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Date of Birth is in invalid form.")
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @NotEmpty(message = "Driving Car Licence Number Must Not Empty.")
    @Pattern(regexp = "[1-9]{1}[A-Z]{1}[0-9]{4}", message = "Driving Car Licence Number is in invalid form.")
    @JsonProperty("drivingCarLicenceNumber")
    private String drivingCarLicenceNumber;

    @NotNull(message = "Rental Price Must Not Empty.")
    @JsonProperty("rentalPrice")
    private int rentalPrice;
}
