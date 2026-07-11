package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepo extends JpaRepository<Packages,Long> {
}
