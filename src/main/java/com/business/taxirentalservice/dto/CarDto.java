package com.business.taxirentalservice.dto;

import com.business.taxirentalservice.constant.FuelType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class CarDto {
    @NotBlank(message = "Licence Number Must Not Empty")
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}", message = "Licence Number is in invalid form.")
    @JsonProperty("licenceNumber")
    private String licenceNumber;

    @NotBlank(message = "Licence Due Date Must Not Empty")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Licence Due Date is in invalid form.")
    @JsonProperty("licenceDueDate")
    private String licenceDueDate;

    @NotBlank(message = "Issued Region Must Not Empty")
    @JsonProperty("issuedRegion")
    private String issuedRegion;

    @NotBlank(message = "Model Must Not Empty")
    @JsonProperty("model")
    private String model;

    @NotNull(message = "Fuel Type Must Not Empty")
    @JsonProperty("fuelType")
    private FuelType fuelType;

    @JsonProperty("cngId")
    @Pattern(regexp = "[A-Z][0-9]{5}", message = "CNG Bought Date is in invalid form.")
    private String cngId;

    @Min(value = 1,message = "Cng Capacity Must Be Greater Than 0.")
    @JsonProperty("capacity")
    private double capacity;

    @Min(value = 1, message = "CNG Price Must Be Greater Than 0.")
    @JsonProperty("cngPrice")
    private int cngPrice;

    @JsonProperty("cngBoughtDate")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "CNG Bought Date is in invalid form.")
    private String cngBoughtDate;

    @JsonProperty("cngDueDate")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "CNG Due Date is in invalid form.")
    private String cngDueDate;

    @Min(value = 1, message = "Car Price Must Be Greater Than 0.")
    @JsonProperty("carPrice")
    private int carPrice;
}
