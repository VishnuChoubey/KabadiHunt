package com.KabadiHunt.repositories;

import com.KabadiHunt.models.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
    Optional<Owner> findByOrganisationId(String organisationId); // custom name
    Optional<Owner> findByEmail(String email);

    List<Owner> findByState(String state);
}
