package com.business.taxirentalservice.controller;

import com.business.taxirentalservice.dto.DebtClearingDto;
import com.business.taxirentalservice.dto.DriverDebtDto;
import com.business.taxirentalservice.dto.ExpenseDto;
import com.business.taxirentalservice.dto.IncomeRequest;
import com.business.taxirentalservice.service.FinanceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    private final Logger logger = LogManager.getLogger(FinanceController.class);

    @PostMapping(value = "/finances/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> recordIncome(@Valid @RequestBody IncomeRequest incomeRequest) {
        logger.info("fetch income request >>> {}", incomeRequest);

        String result = financeService.recordIncome(incomeRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/finances/debt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> recordDebt(@Valid @RequestBody DriverDebtDto driverDebtDto) {
        logger.info("fetch driver debt request >>> {}", driverDebtDto);

        String result = financeService.recordDriverDebt(driverDebtDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/finances/clearing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> recordDebtClearing(@Valid @RequestBody DebtClearingDto debtClearingDto) {
        logger.info("fetch debt clearing request >>> {}", debtClearingDto);

        String result = financeService.recordDebtClearing(debtClearingDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/debt/{licenceNumber}")
    public ResponseEntity<?> fetchTotalDebt(@PathVariable("licenceNumber") String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        int result = financeService.fetchTotalDebt(licenceNumber);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/profit/{licenceNumber}")
    public ResponseEntity<?> fetchProfit(@PathVariable("licenceNumber") String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        int result = financeService.fetchNetProfit(licenceNumber);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/debtlist/{licenceNumber}")
    public ResponseEntity<?> fetchALLDriverDebt(@PathVariable("licenceNumber") String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        List<DriverDebtDto> result = financeService.fetchAllDriverDebt(licenceNumber);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/clearinglist/{licenceNumber}")
    public ResponseEntity<?> fetchAllDebtClearing(@PathVariable("licenceNumber") String licenceNumber) {
        logger.info("fetch requested licence number >>> {}", licenceNumber);

        List<DebtClearingDto> result = financeService.fetchAllDebtClearing(licenceNumber);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/finances/expense")
    public ResponseEntity<?> fetchAllExpenses()
    {
        List<ExpenseDto> result = financeService.fetchAllExpenses();

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
