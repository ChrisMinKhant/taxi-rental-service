package com.business.taxirentalservice.service;

import com.business.taxirentalservice.dto.DueDateRequest;

public interface LicenceService {
    String updateDueDate(DueDateRequest dueDateRequest);

    String removeLicence(String licenceNumber);
}
