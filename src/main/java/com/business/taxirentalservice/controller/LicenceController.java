package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.dto.DueDateRequest;
import com.business.taxirentalservice.service.LicenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
public class LicenceController {
    @Autowired
    private LicenceService licenceService;

    private Logger logger = LogManager.getLogger(LicenceController.class);

    @PutMapping(value = "/licences", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDueDate(@Valid @RequestBody DueDateRequest dueDateRequest)
    {
        logger.info("Fetch Due Date Request >>> {}", dueDateRequest);
        String result = licenceService.updateDueDate(dueDateRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/licences/{licenceNumber}")
    private ResponseEntity<?> removeLicence(@PathVariable("licenceNumber") String licenceNumber)
    {
        logger.info("Fetch Licence Number >>> {}",licenceNumber);
        String result = licenceService.removeLicence(licenceNumber);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
