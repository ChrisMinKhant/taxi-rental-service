package com.business.taxirentalservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("CAR_PART_INVENTORY")
public class CarPartInventory {
    @Id
    private String id;
    private CarPart part;
    private int quantity;
    private int totalPrice;
}
