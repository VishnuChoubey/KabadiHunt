package com.KabadiHunt.repositories;



import java.util.List;
import java.util.Optional;

import com.KabadiHunt.models.Token;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByToken(String token);
    List<Token> findAllByUser_IdAndExpiredIsFalseAndRevokedIsFalse(String userId);
}