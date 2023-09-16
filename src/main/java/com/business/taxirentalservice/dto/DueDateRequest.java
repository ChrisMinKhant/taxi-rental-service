package com.business.taxirentalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class DueDateRequest {
    @JsonProperty("licenceNumber")
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}||[A-Z][0-9]{7}", message = "licence number is in invalid form.")
    @NotBlank(message = "licence number must not be empty.")
    private String licenceNumber;

    @JsonProperty("updatedDueDate")
    @NotBlank(message = "licence due date must not be empty")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "licence due date is in invalid form.")
    private String updatedDueDate;
}
