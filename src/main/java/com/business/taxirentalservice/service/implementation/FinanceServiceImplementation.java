package com.business.taxirentalservice.service.implementation;

import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.dto.DebtClearingDto;
import com.business.taxirentalservice.dto.DriverDebtDto;
import com.business.taxirentalservice.dto.ExpenseDto;
import com.business.taxirentalservice.dto.IncomeRequest;
import com.business.taxirentalservice.model.*;
import com.business.taxirentalservice.repository.*;
import com.business.taxirentalservice.service.DriverService;
import com.business.taxirentalservice.service.FinanceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FinanceServiceImplementation implements FinanceService {
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private DriverDebtRepository driverDebtRepository;
    @Autowired
    private DebtClearingRepository debtClearingRepository;
    @Autowired
    private DriverService driverService;

    private final GeneralResponse response = new GeneralResponse();

    private final Logger logger = LogManager.getLogger(FinanceServiceImplementation.class);

    @Override
    public String recordIncome(IncomeRequest incomeRequest) {

        int expectedRentalPrice = driverService.fetchExpectedRentalPrice(incomeRequest.getDriverLicence());

        if (expectedRentalPrice != 0) {
            Income temporaryIncomeRecord = Income.builder().entryId(UUID.randomUUID().toString())
                    .driverLicence(incomeRequest.getDriverLicence())
                    .entryAmount(incomeRequest.getEntryAmount())
                    .entryDate(LocalDateTime.now())
                    .description(incomeRequest.getDescription())
                    .build();

            if (expectedRentalPrice > incomeRequest.getEntryAmount()) {
                DriverDebt temporaryDriverDebtRecord = DriverDebt.builder().entryId(UUID.randomUUID().toString())
                        .driverLicence(incomeRequest.getDriverLicence())
                        .entryAmount(expectedRentalPrice - incomeRequest.getEntryAmount())
                        .entryDate(LocalDateTime.now())
                        .description(incomeRequest.getDescription())
                        .build();

                incomeRepository.save(temporaryIncomeRecord);
                driverDebtRepository.save(temporaryDriverDebtRecord);

                return response.ACT;
            }

            incomeRepository.save(temporaryIncomeRecord);
            return response.ACT;
        }

        return response.DNF;
    }

    @Override
    public String recordDriverDebt(DriverDebtDto driverDebtDto) {
        if (driverService.fetchDriver(driverDebtDto.getDriverLicence()) != null) {

            DriverDebt temporaryDriverDebt = DriverDebt.builder()
                    .entryId(UUID.randomUUID().toString())
                    .driverLicence(driverDebtDto.getDriverLicence())
                    .entryDate(LocalDateTime.now())
                    .entryAmount(driverDebtDto.getDriverDebtEntryAmount())
                    .description(driverDebtDto.getDescription())
                    .build();

            if (driverDebtDto.getExpenseEntryAmount() != 0) {
                Expense temporaryExpense = Expense.builder()
                        .entryId(UUID.randomUUID().toString())
                        .driverLicence(driverDebtDto.getDriverLicence())
                        .entryDate(LocalDateTime.now())
                        .entryAmount(driverDebtDto.getExpenseEntryAmount())
                        .description(driverDebtDto.getDescription())
                        .build();

                driverDebtRepository.save(temporaryDriverDebt);
                expenseRepository.save(temporaryExpense);
            }

            driverDebtRepository.save(temporaryDriverDebt);

            return response.ACT;
        }

        return response.DNF;
    }

    @Override
    public String recordDebtClearing(DebtClearingDto debtClearingDto) {

        if (driverService.fetchDriver(debtClearingDto.getDriverLicence()) != null) {
            DebtClearing temporaryDebtClearing = DebtClearing.builder()
                    .entryId(UUID.randomUUID().toString())
                    .driverLicence(debtClearingDto.getDriverLicence())
                    .entryAmount(debtClearingDto.getEntryAmount())
                    .entryDate(LocalDateTime.now())
                    .description(debtClearingDto.getDescription())
                    .build();

            debtClearingRepository.save(temporaryDebtClearing);

            return response.ACT;
        }

        return response.DNF;
    }

    @Override
    public int fetchTotalDebt(String driverLicence) {

        if (driverService.fetchDriver(driverLicence) != null) {
            int temporaryDriverDebts = 0;
            int temporaryDebtClearings = 0;
            List<DriverDebt> driverDebtList = driverDebtRepository.findAllByLicenceNumber(driverLicence);
            List<DebtClearing> debtClearingList = debtClearingRepository.findAllByLicenceNumber(driverLicence);

            for (DriverDebt driverDebt : driverDebtList) {
                temporaryDriverDebts += driverDebt.getEntryAmount();
            }

            for (DebtClearing debtClearing : debtClearingList) {
                temporaryDebtClearings += debtClearing.getEntryAmount();
            }

            return temporaryDriverDebts - temporaryDebtClearings;

        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, response.DNF);
    }

    @Override
    public int fetchNetProfit(String driverLicence) {
        if (driverService.fetchDriver(driverLicence) != null) {
            int temporaryIncome = 0;
            int temporaryExpense = 0;
            int temporaryTotalDebt = fetchTotalDebt(driverLicence);

            List<Income> incomeList = incomeRepository.findAllByLicenceNumber(driverLicence);
            List<Expense> expenseList = expenseRepository.findAllByLicenceNumber(driverLicence);

            for (Income income : incomeList) {
                temporaryIncome += income.getEntryAmount();
            }

            for (Expense expense : expenseList) {
                temporaryExpense += expense.getEntryAmount();
            }

            return temporaryIncome - temporaryExpense - temporaryTotalDebt;

        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, response.DNF);
    }

    @Override
    public List<DriverDebtDto> fetchAllDriverDebt(String driverLicence) {
        if (driverService.fetchDriver(driverLicence) != null) {
            List<DriverDebtDto> driverDebtDtoList = new ArrayList<>();

            List<DriverDebt> driverDebtList = driverDebtRepository.findAllByLicenceNumber(driverLicence);

            for (DriverDebt driverDebt : driverDebtList) {
                driverDebtDtoList.add(DriverDebtDto.builder()
                        .driverLicence(driverDebt.getDriverLicence())
                        .driverDebtEntryAmount(driverDebt.getEntryAmount())
                        .entryDate(driverDebt.getEntryDate().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")))
                        .description(driverDebt.getDescription())
                        .build());
            }

            return driverDebtDtoList;

        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, response.DNF);
    }

    @Override
    public List<DebtClearingDto> fetchAllDebtClearing(String driverLicence) {

        if (driverService.fetchDriver(driverLicence) != null) {
            List<DebtClearingDto> debtClearingDtoList = new ArrayList<>();

            List<DebtClearing> debtClearingList = debtClearingRepository.findAllByLicenceNumber(driverLicence);

            for (DebtClearing debtClearing : debtClearingList) {
                debtClearingDtoList.add(DebtClearingDto.builder()
                        .driverLicence(debtClearing.getDriverLicence())
                        .entryAmount(debtClearing.getEntryAmount())
                        .entryDate(debtClearing.getEntryDate().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")))
                        .description(debtClearing.getDescription())
                        .build());
            }

            return debtClearingDtoList;

        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, response.DNF);
    }

    @Override
    public List<ExpenseDto> fetchAllExpenses() {
        List<Expense> temporaryExpenseList = expenseRepository.findAll();
        List<ExpenseDto> temporaryExpenseDtoList = new ArrayList<>();

        for (Expense expense : temporaryExpenseList) {
            temporaryExpenseDtoList.add(ExpenseDto.builder()
                    .driverLicence(expense.getDriverLicence())
                    .entryDate(expense.getEntryDate().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm")))
                    .entryAmount(expense.getEntryAmount())
                    .description(expense.getDescription())
                    .build());
        }

        return temporaryExpenseDtoList;
    }
}
