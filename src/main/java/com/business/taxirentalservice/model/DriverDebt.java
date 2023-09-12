package com.business.taxirentalservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("DRIVER_DEBT")
public class DriverDebt {
    @Id
    private String entryId;
    private Driver driver;
    private LocalDateTime entryDate;
    private int entryAmount;
    private String description;
}
