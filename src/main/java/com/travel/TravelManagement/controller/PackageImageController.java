package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.model.PackageImages;
import com.travel.TravelManagement.services.PackageImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin("*")
public class PackageImageController {

    @Autowired
    private PackageImageService service;

    @PostMapping(value = "/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<PackageImages> addImage(@RequestParam Long packageId, @RequestParam("imageUrl") MultipartFile image) throws IOException {
        return service.addImage(packageId,image);
    }

    @GetMapping("/all")
    public List<PackageImages> getAllImages() {
        return service.getAllImages();
    }

//    @GetMapping("/{id}")
//    public PackageImage getImageById(@PathVariable Long id ,@RequestParam MultipartFile image) throws IOException {
//        return service.getImageById(id,image);
//    }

    @PutMapping("/update/{id}")
    public List<PackageImages> updateImage(
            @PathVariable Long id,
            @RequestParam MultipartFile image) throws IOException {

        return service.updateImage(id, image);
    }

    @DeleteMapping("/delete/{id}")
    public List<PackageImages> deleteImage(@PathVariable Long id) throws IOException {
        return service.deleteImage(id);
    }
}