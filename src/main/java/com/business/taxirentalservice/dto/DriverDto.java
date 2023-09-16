package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class DriverDto {
    @NotBlank(message = "Driving Licence Number Must Not Empty.")
    @Pattern(regexp = "[A-Z][0-9]{7}", message = "Driving Licence Number is in invalid form.")
    @JsonProperty("drivingLicenceNumber")
    private String drivingLicenceNumber;

    @NotBlank(message = "Licence Due Date Must Not Empty.")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Licence Due Date is in invalid form.")
    @JsonProperty("licenceDueDate")
    private String licenceDueDate;

    @NotBlank(message = "Issued Region Must Not Empty.")
    @JsonProperty("issuedRegion")
    private String issuedRegion;

    @NotBlank(message = "Name Must Not Empty.")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Address Must Not Empty.")
    @JsonProperty("address")
    private String address;

    @NotBlank(message = "Phone Must Not Empty.")
    @JsonProperty("phone")
    private String phone;

    @NotBlank(message = "Date of Birth Must Not Empty.")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Date of Birth is in invalid form.")
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @NotBlank(message = "Driving Car Licence Number Must Not Empty.")
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}", message = "Driving Car Licence Number is in invalid form.")
    @JsonProperty("drivingCarLicenceNumber")
    private String drivingCarLicenceNumber;

    @Min(value = 1,message = "Rental Price Must Be Greater than 0.")
    @JsonProperty("rentalPrice")
    private int rentalPrice;
}
