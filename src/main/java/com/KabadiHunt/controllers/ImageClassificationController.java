package com.KabadiHunt.controllers;

import com.KabadiHunt.dto.ClassificationResultDto;
import com.KabadiHunt.services.ImageClassificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageClassificationController {

    private final ImageClassificationService imageService;

    public ImageClassificationController(ImageClassificationService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/classify")
    public ResponseEntity<ClassificationResultDto> classifyImage(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(imageService.classifyImage(image));
    }
}
