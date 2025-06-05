package com.KabadiHunt.repositories;

import com.KabadiHunt.models.Payments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payments, String> {
    List<Payments> findByUserId(String userId);
    List<Payments> findByOwnerId(String ownerId);
    Optional<Payments> findByTransactionId(String transactionId);
    List<Payments> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}
