package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.DebtClearing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtClearingRepository extends MongoRepository<DebtClearing,String> {
}
