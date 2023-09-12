package com.business.taxirentalservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document("CNG")
public class CNG {
    @Id
    private String id;
    private double capacity;
    private int price;
    private LocalDate boughtDate;
    private LocalDate dueDate;
}
