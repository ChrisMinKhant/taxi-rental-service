package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class DriverDto {

    @Pattern(regexp = "[A-Z][0-9]{7}", message = "driver licence is in invalid form.")
    @NotBlank(message = "driver licence must not be empty.")
    @JsonProperty("driverLicence")
    private String driverLicence;

    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "licence due date is in invalid form.")
    @NotBlank(message = "licence due date must not be empty.")
    @JsonProperty("licenceDueDate")
    private String licenceDueDate;

    @NotBlank(message = "issued region must not be empty.")
    @JsonProperty("issuedRegion")
    private String issuedRegion;

    @NotBlank(message = "name must not be empty.")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "address must not be empty.")
    @JsonProperty("address")
    private String address;

    @NotBlank(message = "phone must not be empty.")
    @JsonProperty("phone")
    private String phone;

    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "date of birth is in invalid form.")
    @NotBlank(message = "date of birth must not be empty.")
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @Pattern(regexp = "[A-Z]{2}[0-9]{4}", message = "driving car licence number is in invalid form.")
    @NotBlank(message = "driving car licence number must not be empty.")
    @JsonProperty("drivingCarLicenceNumber")
    private String drivingCarLicenceNumber;

    @Min(value = 1,message = "rental price must be greater than 0.")
    @JsonProperty("rentalPrice")
    private int rentalPrice;
}
