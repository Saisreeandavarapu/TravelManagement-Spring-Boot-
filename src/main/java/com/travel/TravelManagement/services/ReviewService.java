package com.travel.TravelManagement.services;

import com.travel.TravelManagement.model.Packages;
import com.travel.TravelManagement.model.Registration;
import com.travel.TravelManagement.model.Review;
import com.travel.TravelManagement.repo.PackageRepo;
import com.travel.TravelManagement.repo.RegistrationRepo;
import com.travel.TravelManagement.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private RegistrationRepo registrationRepo;
    @Autowired
    private PackageRepo packageRepo;
    public List<Review> addReview(Review review) {
        Registration regId=registrationRepo.findById(review.getRegistration().getId()).get();
        Packages packId=packageRepo.findById(review.getAPackage().getId()).get();
        review.setRegistration(regId);
        review.setAPackage(packId);
         reviewRepo.save(review);
         return reviewRepo.findAll();
    }

    public List<Review> getReviewByPackageId(Long id) {
        return reviewRepo.findByaPackageId(id);
    }

    public List<Review> deleteReviewById(Long id) {
         reviewRepo.deleteById(id);
         return reviewRepo.findAll();
    }

    public List<Review> addSllReviews(List<Review> reviews) {
        return reviewRepo.saveAll(reviews);
    }
}
