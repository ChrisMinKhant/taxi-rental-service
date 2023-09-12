package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.Income;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends MongoRepository<Income,String> {
}
