//package com.ScrapBridge.services;
//
//import com.ScrapBridge.dto.ApiResponseDto;
//import com.ScrapBridge.dto.ContactFormDto;
//import com.ScrapBridge.models.ContactForm;
//import com.ScrapBridge.repositories.ContactFormRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ContactService {
//    @Autowired
//    private final ContactFormRepository contactFormRepository;
//
//    public ApiResponseDto submitContactDetails(ContactFormDto contactFormDto) {
//        ContactForm contactForm = new ContactForm();
//        contactForm.setName(contactFormDto.getName());
//        contactForm.setEmail(contactFormDto.getEmail());
//        contactForm.setPhoneNumber(contactFormDto.getPhoneNumber());
//        contactForm.setMessage(contactFormDto.getMessage());
//        contactFormRepository.save(contactForm);
//        return new ApiResponseDto(true, "Contact details submitted successfully");
//    }
//}
