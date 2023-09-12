package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.CarPartInventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPartInventoryRepository extends MongoRepository<CarPartInventory,String> {
}
