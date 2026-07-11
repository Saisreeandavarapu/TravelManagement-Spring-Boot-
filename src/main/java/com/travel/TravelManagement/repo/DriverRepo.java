package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends JpaRepository<Driver,Long> {
}
