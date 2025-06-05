package com.KabadiHunt.controllers;

import com.KabadiHunt.dto.OwnerDto;
import com.KabadiHunt.services.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OwnerController {
    public static final String UPLOAD_DIR = "uploads";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner/all")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/owner/{user_id}")
    public ResponseEntity<OwnerDto> getOwnerDetails(@PathVariable("user_id") String userId) {
        return ResponseEntity.ok(ownerService.getOwnerDetails(userId));
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<OwnerDto> updateOwnerDetails(
            @PathVariable("user_id")String organisationId,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("street") String street,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("zipcode") String zipcode,
            @RequestParam("latitude")Double lat,
            @RequestParam("longitude")Double lon,
            @RequestParam("organisation_name")String organisationName,
            @RequestParam(value = "image", required = false) MultipartFile image
    )throws IOException {
        String imagePath = null;

        if (image != null && !image.isEmpty()) {
            String originalFilename = StringUtils.cleanPath(image.getOriginalFilename());
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String newFilename = UUID.randomUUID() + extension;

            File uploadFolder = new File(UPLOAD_DIR);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();

            Path filepath = Paths.get(UPLOAD_DIR, newFilename);
            Files.copy(image.getInputStream(), filepath);

            imagePath = "/uploads/" + newFilename;

        }
        OwnerDto updatedOwner = ownerService.updateOwner(organisationId, email,phone, street, city, state, zipcode,lat,lon,organisationName, imagePath);
        return ResponseEntity.ok(updatedOwner);
    }
}
