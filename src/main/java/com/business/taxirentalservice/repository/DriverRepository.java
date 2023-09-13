package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends MongoRepository<Driver,String> {
    @Query("{'drivingCarLicence':?0}")
    Optional<Driver> findByDrivingCarLicenceNumber(String drivingCarLicenceNumber);
}
