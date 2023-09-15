package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.DriverDebt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverDebtRepository extends MongoRepository<DriverDebt,String> {
    @Query("{'driverLicence':?0}")
    List<DriverDebt> findAllByLicenceNumber(String licenceNumber);
}
