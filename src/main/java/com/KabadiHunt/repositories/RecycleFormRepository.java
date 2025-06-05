package com.KabadiHunt.repositories;

import com.KabadiHunt.models.RecycleForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecycleFormRepository extends MongoRepository<RecycleForm, String> {
    List<RecycleForm> findByUserId(String userId);
    List<RecycleForm> findByOrganisationId(String organisationId);
    List<RecycleForm> findByOrganisation_OrganisationIdAndStatus(String organisationId, Boolean status);
    Optional<RecycleForm> findByOrderId(String orderId);
    List<RecycleForm> findByStatus(Boolean status);
}
