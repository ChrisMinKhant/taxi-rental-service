package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class DecreaseCarPartDto {
    @JsonProperty("carPartName")
    @NotBlank(message = "Car Part Must Not Be Empty.")
    private String carPartName;

    @JsonProperty("quantity")
    @Min(value = 1, message = "Quantity Must Greater Than O.")
    private int quantity;
}
