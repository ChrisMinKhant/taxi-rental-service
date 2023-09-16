package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.CarPartInventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarPartInventoryRepository extends MongoRepository<CarPartInventory,String> {
    @Query("{'carPartName':?0}")
    Optional<CarPartInventory> findByCarPartName(String carPartName);

}
