//package com.ScrapBridge.controllers;
//
//import com.ScrapBridge.dto.ApiResponseDto;
//import com.ScrapBridge.dto.ContactFormDto;
//import com.ScrapBridge.services.ContactService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/contact")
//public class ContactController {
//
//    private final ContactService contactService;
//
//    public ContactController(ContactService contactService) {
//        this.contactService = contactService;
//    }
//
//    @PostMapping("/submit")
//    public ResponseEntity<ApiResponseDto> submitContactDetails(@RequestBody ContactFormDto contactForm) {
//        return ResponseEntity.ok(contactService.submitContactDetails(contactForm));
//    }
//}
