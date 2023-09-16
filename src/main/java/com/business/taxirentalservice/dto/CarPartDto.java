package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CarPartDto {
    @JsonProperty("carPartName")
    @NotBlank(message = "Car Part Must Not Be Empty.")
    private String carPartName;

    @JsonProperty("quantity")
    @Min(value = 1, message = "Quantity Must Greater Than O.")
    private int quantity;

    @JsonProperty("price")
    @Min(value = 1, message = "Price Must Greater Than O.")
    private int price;
}
