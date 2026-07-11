package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.PackageImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageImageRepo extends JpaRepository<PackageImages,Long> {
}
