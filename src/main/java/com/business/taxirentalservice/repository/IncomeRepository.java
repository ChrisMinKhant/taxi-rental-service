package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.Income;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends MongoRepository<Income,String> {
    @Query("{'driverLicence':?0}")
    List<Income> findAllByLicenceNumber(String licenceNumber);
}
