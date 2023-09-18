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
public class ExpenseDto {

    @NotBlank(message = "driving licence must not be empty.")
    @Pattern(regexp = "[A-Z][0-9]{7}", message = "driving licence is in invalid form.")
    @JsonProperty("driverLicence")
    private String driverLicence;

    @Min(value = 1, message = "entry amount must be greater than 0.")
    @JsonProperty("entryAmount")
    private int entryAmount;

    @JsonIgnore
    @JsonProperty("entryDate")
    private String entryDate;

    @NotBlank(message = "Description must not be empty.")
    @JsonProperty("description")
    private String description;
}
