package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.dto.DueDateRequest;
import com.business.taxirentalservice.service.LicenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequestMapping("/api")
@RestController
@Api(value = "Licence", tags = "Licence Endpoints")
public class LicenceController {
    @Autowired
    private LicenceService licenceService;

    private Logger logger = LogManager.getLogger(LicenceController.class);

    private final GeneralResponse response = new GeneralResponse();

    @PutMapping(value = "/licences", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Due Date")
    public ResponseEntity<?> updateDueDate(@Valid @RequestBody DueDateRequest dueDateRequest)
    {
        logger.info("fetch requested due date >>> {}", dueDateRequest);
        String result = licenceService.updateDueDate(dueDateRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/licences/{licenceNumber}")
    @ApiOperation(value = "Remove Licence")
    private ResponseEntity<?> removeLicence(@PathVariable("licenceNumber") @NotBlank(message = "licence number must not be empty.") String licenceNumber)
    {
        logger.info("fetch requested licence number >>> {}",licenceNumber);
        String result = licenceService.removeLicence(licenceNumber);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
