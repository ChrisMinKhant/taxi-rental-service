package com.business.taxirentalservice.service;

import com.business.taxirentalservice.dto.DebtClearingDto;
import com.business.taxirentalservice.dto.DriverDebtDto;
import com.business.taxirentalservice.dto.ExpenseDto;
import com.business.taxirentalservice.dto.IncomeRequest;

import java.util.List;

public interface FinanceService {
    String recordIncome(IncomeRequest incomeRequest);
    String recordDriverDebt(DriverDebtDto driverDebtDto);
    String recordDebtClearing(DebtClearingDto debtClearingDto);
    int fetchTotalDebt(String driverLicence);
    int fetchNetProfit(String driverLicence);
    List<DriverDebtDto> fetchAllDriverDebt(String driverLicence);
    List<DebtClearingDto> fetchAllDebtClearing(String driverLicence);
    List<ExpenseDto> fetchAllExpenses();
}
