package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class DriverDebtDto {
    @NotBlank(message = "Driving Licence Number Must Not Empty.")
    @Pattern(regexp = "[A-Z]{1}[0-9]{7}", message = "Driving Licence Number is in invalid form.")
    @JsonProperty("driverLicence")
    private String driverLicence;

    @Min(value = 1, message = "Driver Debt Must Be Greater than 0.")
    @JsonProperty("driverDebtEntryAmount")
    private int driverDebtEntryAmount;

    @JsonProperty("expenseEntryAmount")
    @JsonIgnore
    private int expenseEntryAmount;

    @JsonProperty("entryDate")
    @JsonIgnore
    private String entryDate;

    @NotBlank(message = "Description Must Not Empty")
    @JsonProperty("description")
    private String description;
}
