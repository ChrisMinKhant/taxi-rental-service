package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class DebtClearingDto {
    @NotBlank(message = "Driving Licence Number Must Not Empty.")
    @Pattern(regexp = "[A-Z][0-9]{7}", message = "Driving Licence Number is in invalid form.")
    @JsonProperty("driverLicence")
    private String driverLicence;

    @Min(value = 1, message = "Entry Amount Must Be Greater Than 0.")
    @JsonProperty("entryAmount")
    private int entryAmount;

    @JsonProperty("entryDate")
    @JsonIgnore
    private String entryDate;

    @NotBlank(message = "Description Must Not Empty")
    @JsonProperty("description")
    private String description;
}
