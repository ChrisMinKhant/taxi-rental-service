package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.DriverDebt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverDebtRepository extends MongoRepository<DriverDebt,String> {
}
