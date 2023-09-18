package com.business.taxirentalservice.dto;

import com.business.taxirentalservice.constant.FuelType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class CarDto {

    @Pattern(regexp = "[A-Z]{2}[0-9]{4}", message = "licence number is in invalid form.")
    @NotBlank(message = "licence number must not be empty.")
    @JsonProperty("licenceNumber")
    private String licenceNumber;

    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "licence due date is in invalid form.")
    @NotBlank(message = "licence due date must not be empty.")
    @JsonProperty("licenceDueDate")
    private String licenceDueDate;

    @NotBlank(message = "issued region must not be empty.")
    @JsonProperty("issuedRegion")
    private String issuedRegion;

    @NotBlank(message = "model must not be empty.")
    @JsonProperty("model")
    private String model;

    @NotNull(message = "fuel type must not be empty.")
    @JsonProperty("fuelType")
    private FuelType fuelType;

    @Pattern(regexp = "[A-Z][0-9]{5}", message = "cng bought date is in invalid form.")
    @JsonProperty("cngId")
    private String cngId;

    @Min(value = 1,message = "cng capacity must be greater than 0.")
    @JsonProperty("capacity")
    private double capacity;

    @Min(value = 1, message = "cng price must be greater than 0.")
    @JsonProperty("cngPrice")
    private int cngPrice;

    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "cng bought date is in invalid form.")
    @JsonProperty("cngBoughtDate")
    private String cngBoughtDate;

    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "cng due date is in invalid form.")
    @JsonProperty("cngDueDate")
    private String cngDueDate;

    @Min(value = 1, message = "car price must be greater than 0.")
    @JsonProperty("carPrice")
    private int carPrice;
}
