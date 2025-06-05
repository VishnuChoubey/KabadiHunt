package com.KabadiHunt.controllers;



import com.KabadiHunt.dto.ApiResponseDto;
import com.KabadiHunt.dto.RecycleFormRequestDto;
import com.KabadiHunt.dto.RecycleFormResponseDto;

import com.KabadiHunt.services.RecycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequiredArgsConstructor
public class RecycleController {

    public static final String UPLOAD_DIR = "uploads";
    @Autowired
    private RecycleService recycleService;

    // Endpoint to get owner detail by user_id (GET)
//    @GetMapping("/owner/{user_id}/")
//    public ResponseEntity<?> getOwnerByUserId(@PathVariable("user_id") String userId) {
//        // Your service method should return owner data for the frontend
//        // Assuming you have a method in RecycleService or another service that returns owner info by userId
//        // For example:
//        // OwnerDto owner = recycleService.getOwnerByUserId(userId);
//        // return ResponseEntity.ok(owner);
//
//        // Placeholder return to avoid compile errors:
//        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented yet");
//    }

    // Submit scrap request - POST with multipart/form-data
    @PostMapping("/scrap-request")
    public ResponseEntity<RecycleFormResponseDto> submitScrapRequest(
            @RequestParam("item_type") String itemType,
            @RequestParam("date") String date,
            @RequestParam("phone") String phone,
            @RequestParam("weight")Integer weight,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("longitude") Double longitude,
            @RequestParam("latitude")Double latitude,
            @RequestParam("user") String userId,
            @RequestParam("organisation") String organisationId

    ) throws IOException {
        // Map multipart request to RecycleFormRequestDto
        RecycleFormRequestDto requestDto = new RecycleFormRequestDto();
        requestDto.setItemType(itemType);
        requestDto.setDate(date);
        requestDto.setPhone(phone);
        requestDto.setWeight(weight);
        requestDto.setLatitude(latitude);
        requestDto.setLongitude(longitude);
        requestDto.setUserId(userId);
        requestDto.setOrganisationId(organisationId);


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
            requestDto.setImagePath(imagePath);

        }

        var responseDto = recycleService.submitScrapRequest(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // Get all orders for a user (GET)
    @GetMapping("/orders/{user_id}")
    public ResponseEntity<List<RecycleFormResponseDto>> getAllOrders(@PathVariable("user_id") String userId) {
        List<RecycleFormResponseDto> orders = recycleService.getAllOrders(userId);
        return ResponseEntity.ok(orders);
    }

    // Get order detail by order ID (GET)
    @GetMapping("/order/{order_id}")
    public ResponseEntity<RecycleFormResponseDto> getOrderDetail(@PathVariable("order_id") String orderId) {
        RecycleFormResponseDto order = recycleService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }

    // Accept order (PUT or POST)
    @PostMapping("/order/{order_id}/accept")
    public ResponseEntity<ApiResponseDto> acceptOrder(@PathVariable("order_id") String orderId) {
        ApiResponseDto response = recycleService.orderAccepted(orderId);
        return ResponseEntity.ok(response);
    }
//
    // Reject order (POST) with reason
    @PostMapping("/order/{order_id}/reject")
    public ResponseEntity<ApiResponseDto> rejectOrder(@PathVariable("order_id") String orderId, @RequestParam("reason") String reason) {
        ApiResponseDto response = recycleService.orderRejected(orderId, reason);
        return ResponseEntity.ok(response);
    }
}

