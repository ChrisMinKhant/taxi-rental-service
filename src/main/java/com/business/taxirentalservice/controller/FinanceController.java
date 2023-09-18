package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.dto.DebtClearingDto;
import com.business.taxirentalservice.dto.DriverDebtDto;
import com.business.taxirentalservice.dto.ExpenseDto;
import com.business.taxirentalservice.dto.IncomeRequest;
import com.business.taxirentalservice.service.FinanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@Api(value = "Finance", tags = "Finance Endpoints")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    private final Logger logger = LogManager.getLogger(FinanceController.class);

    private final GeneralResponse response = new GeneralResponse();

    @PostMapping(value = "/finances/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Record Income")
    public ResponseEntity<?> recordIncome(@Valid @RequestBody IncomeRequest incomeRequest) {
        logger.info("fetch requested income >>> {}", incomeRequest);

        String result = financeService.recordIncome(incomeRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/finances/debt", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Record Debt")
    public ResponseEntity<?> recordDebt(@Valid @RequestBody DriverDebtDto driverDebtDto) {
        logger.info("fetch request driver debt >>> {}", driverDebtDto);

        String result = financeService.recordDriverDebt(driverDebtDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/finances/clearing", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Record Debt Clearing")
    public ResponseEntity<?> recordDebtClearing(@Valid @RequestBody DebtClearingDto debtClearingDto) {
        logger.info("fetch request debt clearing >>> {}", debtClearingDto);

        String result = financeService.recordDebtClearing(debtClearingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/debt/{licenceNumber}")
    @ApiOperation(value = "Fetch Total Debt")
    public ResponseEntity<?> fetchTotalDebt(@PathVariable("licenceNumber") @NotBlank( message = "licence number must not be empty." ) String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        int result = financeService.fetchTotalDebt(licenceNumber);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/profit/{licenceNumber}")
    @ApiOperation(value = "Fetch Profit")
    public ResponseEntity<?> fetchProfit(@PathVariable("licenceNumber")  @NotBlank( message = "licence number must not be empty.") String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        int result = financeService.fetchNetProfit(licenceNumber);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/debtlist/{licenceNumber}")
    @ApiOperation(value = "Fetch Driver Debt List")
    public ResponseEntity<?> fetchALLDriverDebt(@PathVariable("licenceNumber")  @NotBlank( message = "licence number must not be empty." ) String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        List<DriverDebtDto> result = financeService.fetchAllDriverDebt(licenceNumber);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/clearinglist/{licenceNumber}")
    @ApiOperation(value = "Fetch Driver Debt Clearing List")
    public ResponseEntity<?> fetchAllDebtClearing(@PathVariable("licenceNumber")  @NotBlank( message = "licence number must not be empty." ) String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        List<DebtClearingDto> result = financeService.fetchAllDebtClearing(licenceNumber);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/expense")
    @ApiOperation(value = "Fetch All Expenses")
    public ResponseEntity<?> fetchAllExpenses()
    {
        List<ExpenseDto> result = financeService.fetchAllExpenses();

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
