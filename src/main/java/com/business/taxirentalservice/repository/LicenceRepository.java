package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.Licence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenceRepository extends MongoRepository<Licence, String> {
}
