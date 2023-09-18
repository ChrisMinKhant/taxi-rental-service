package com.business.taxirentalservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("CAR_PART_INVENTORY")
public class CarPartInventory {
    @Id
    private String id;
    private String carPartName;
    private int quantity;
    private int totalPrice;
}
