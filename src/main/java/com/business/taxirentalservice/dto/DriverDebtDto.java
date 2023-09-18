package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class DriverDebtDto {

    @Pattern(regexp = "[A-Z][0-9]{7}", message = "driving licence is in invalid form.")
    @NotBlank(message = "driving licence must not be empty.")
    @JsonProperty("driverLicence")
    private String driverLicence;

    @Min(value = 1, message = "driver debt must be greater than 0.")
    @JsonProperty("driverDebtEntryAmount")
    private int driverDebtEntryAmount;

    @JsonProperty("expenseEntryAmount")
    private int expenseEntryAmount;

    @JsonProperty("entryDate")
    private String entryDate;

    @NotBlank(message = "description must not be empty.")
    @JsonProperty("description")
    private String description;
}
