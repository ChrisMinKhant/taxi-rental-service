package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class DueDateRequest {

    @Pattern(regexp = "[A-Z]{2}[0-9]{4}||[A-Z][0-9]{7}", message = "licence number is in invalid form.")
    @NotBlank(message = "licence number must not be empty.")
    @JsonProperty("licenceNumber")
    private String licenceNumber;

    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "licence due date is in invalid form.")
    @NotBlank(message = "licence due date must not be empty.")
    @JsonProperty("updatedDueDate")
    private String updatedDueDate;
}
