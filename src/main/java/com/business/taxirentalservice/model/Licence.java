package com.business.taxirentalservice.model;

import com.business.taxirentalservice.constant.LicenceType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document("LICENCE")
public class Licence {
    @Id
    private String licenceNumber;
    private LicenceType type;
    private LocalDate dueDate;
    private String region;
}
