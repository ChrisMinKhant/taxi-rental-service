package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.DebtClearing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtClearingRepository extends MongoRepository<DebtClearing,String> {
    @Query("{'driverLicence':?0}")
    List<DebtClearing> findAllByLicenceNumber(String licenceNumber);
}
