package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
   List<Review>  findByaPackageId(Long id);
}
