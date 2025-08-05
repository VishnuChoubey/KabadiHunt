//package com.KabadiHunt.controllers;
//
//
//import com.KabadiHunt.dto.ApiResponseDto;
//import com.KabadiHunt.dto.PaymentRequestDto;
//import com.KabadiHunt.dto.PaymentResponseDto;
//
//import com.KabadiHunt.services.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//
//@RestController
//@RequestMapping("/api")
//public class PaymentController {
//
//    @Autowired
//    private PaymentService paymentService;
//
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    @PostMapping("/payment")
//    public ResponseEntity<?> createPaymentOrder(@RequestBody PaymentRequestDto request) {
//        try {
//            PaymentResponseDto responseDto = paymentService.createPayment(request);
//            return ResponseEntity.ok(responseDto);
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponseDto(false, "Payment creation failed: " + e.getMessage()));
//        }
//    }
//
//
//    @GetMapping("/payment-status/{order_id}/{username}/{owner_id}/{amount}/{transaction_id}")
//    public ResponseEntity<ApiResponseDto> updatePaymentStatus(
//            @PathVariable("order_id") String orderId,
//            @PathVariable("username") String username,
//            @PathVariable("owner_id") String ownerId,
//            @PathVariable("amount") BigDecimal amount,
//            @PathVariable("transaction_id") String transactionId) {
//
//        ApiResponseDto response = paymentService.processPaymentStatus(
//                orderId,
//                username,
//                ownerId,
//                amount,
//                transactionId
//        );
//
//        return response.getSuccess()
//                ? ResponseEntity.ok(response)
//                : ResponseEntity.badRequest().body(response);
//    }
//}