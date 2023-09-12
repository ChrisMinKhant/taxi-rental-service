package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends MongoRepository<Driver,String> {
}
