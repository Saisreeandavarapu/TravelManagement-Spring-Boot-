package com.travel.TravelManagement.services;

import com.travel.TravelManagement.AdvancedServices.CloudinaryService;
import com.travel.TravelManagement.model.PackageImages;
import com.travel.TravelManagement.model.Packages;
import com.travel.TravelManagement.repo.PackageImageRepo;
import com.travel.TravelManagement.repo.PackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PackageImageService {

    @Autowired
    private CloudinaryService cloudinary;
    @Autowired
    private PackageImageRepo repository;
    @Autowired
    private PackageRepo packageRepo;


    public List<PackageImages> addImage(Long packageId, MultipartFile image) throws IOException {

        Packages pkg = packageRepo.findById(packageId).orElse(null);

        Map result=cloudinary.uploadImage(image);
        PackageImages packageImage=new PackageImages();
        packageImage.setAPackage(pkg);
        packageImage.setImageUrl(result.get("secure_url").toString());
        repository.save(packageImage);
        return repository.findAll();


    }
    public List<PackageImages> getAllImages() {
        return repository.findAll();
    }

    public PackageImages getImageById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image Not Found"));
    }

    public List<PackageImages> updateImage(Long id, MultipartFile image) throws IOException {

        PackageImages existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image Not Found"));

       cloudinary.deleteImage(image.getSize());
       Packages packages = packageRepo.findById(id).get();
       Map result=cloudinary.uploadImage(image);
       existing.setId(packages.getId());
       existing.setImageUrl(result.get("secure_url").toString());


         repository.save(existing);
         return repository.findAll();
    }

    public List<PackageImages> deleteImage(Long id) throws IOException {

        PackageImages existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image Not Found"));

        cloudinary.deleteImage(existing.getId());
        repository.delete(existing);

        return repository.findAll();
    }


}