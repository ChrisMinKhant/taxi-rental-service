package com.business.taxirentalservice.repository;

import com.business.taxirentalservice.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car,String> {
}
