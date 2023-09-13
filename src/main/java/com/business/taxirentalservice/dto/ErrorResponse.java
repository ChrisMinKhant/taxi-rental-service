package com.business.taxirentalservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime time;
    private HttpStatus status;
    private List<String> errors;
}
