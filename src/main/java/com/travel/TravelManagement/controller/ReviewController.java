package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.model.Review;
import com.travel.TravelManagement.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Review")
@CrossOrigin("*")
public class ReviewController {
    @Autowired
    private ReviewService service;
    @PostMapping("/add")
    public List<Review> addReview(@RequestBody Review review){
        return service.addReview(review);
    }
    @GetMapping("/package/{id}")
    public List<Review> getReviewByPackageId(@PathVariable Long id){
        return service.getReviewByPackageId(id);
    }
    @DeleteMapping("/delete/{id}")
    public List<Review> deleteReviewById(@PathVariable Long id)
    {
        return service.deleteReviewById(id);
    }
    @PostMapping("/addAll")
    public List<Review> addAllReviews(@RequestBody List<Review> reviews){
        return service.addSllReviews(reviews);
    }

}
