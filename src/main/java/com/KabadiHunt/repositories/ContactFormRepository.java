//package com.ScrapBridge.repositories;
//
//import com.ScrapBridge.models.ContactForm;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Repository
//public interface ContactFormRepository extends MongoRepository<ContactForm, String> {
//    List<ContactForm> findByEmail(String email);
//    List<ContactForm> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
//}
