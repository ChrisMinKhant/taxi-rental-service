package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.CNG;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CngRepository extends MongoRepository<CNG,String> {
}
