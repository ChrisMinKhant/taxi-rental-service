package com.business.taxirentalservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarListResponse {
    private String licenceNumber;
}
