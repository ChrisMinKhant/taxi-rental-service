package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CarPartDto {

    @NotBlank(message = "car part must not be empty.")
    @JsonProperty("carPartName")
    private String carPartName;

    @Min(value = 1, message = "quantity must be greater than 0.")
    @JsonProperty("quantity")
    private int quantity;

    @Min(value = 1, message = "price must be greater than 0.")
    @JsonProperty("price")
    private int price;
}
