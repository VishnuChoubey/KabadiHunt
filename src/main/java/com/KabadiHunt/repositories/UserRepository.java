package com.KabadiHunt.repositories;

import com.KabadiHunt.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  //  Optional<?> findByUsername(String username);
   // Optional<User> findByEmail(String email);
    Optional<User> findById(String userid);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
   Optional<User> findByEmail(String username);
}
