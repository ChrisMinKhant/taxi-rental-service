package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense,String> {
    @Query("{'driverLicence':?0}")
    List<Expense> findAllByLicenceNumber(String licenceNumber);
}
