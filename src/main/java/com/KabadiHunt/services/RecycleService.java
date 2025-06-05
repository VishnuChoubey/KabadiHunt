package com.KabadiHunt.services;

import com.KabadiHunt.dto.ApiResponseDto;
import com.KabadiHunt.dto.RecycleFormRequestDto;
import com.KabadiHunt.dto.RecycleFormResponseDto;
import com.KabadiHunt.exception.ResourceNotFoundException;
import com.KabadiHunt.models.Owner;
import com.KabadiHunt.models.RecycleForm;
import com.KabadiHunt.models.User;
import com.KabadiHunt.repositories.OwnerRepository;
import com.KabadiHunt.repositories.RecycleFormRepository;
import com.KabadiHunt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecycleService {
    @Autowired
    private RecycleFormRepository recycleFormRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private NotificationService notificationService;

    public RecycleFormResponseDto submitScrapRequest(RecycleFormRequestDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Owner owner = ownerRepository.findById(request.getOrganisationId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        RecycleForm recycleForm = new RecycleForm();
        recycleForm.setUser(user);
        recycleForm.setOrganisation(owner);
        recycleForm.setItemType(request.getItemType());
        recycleForm.setDate(request.getDate());
        recycleForm.setPhone(request.getPhone());
        recycleForm.setImagePath(request.getImagePath());
        recycleForm.setWeight(request.getWeight());
        recycleForm.setLongitude(request.getLongitude());
        recycleForm.setLatitude(request.getLatitude());
        recycleForm.setStatus(false);

        RecycleForm savedForm = recycleFormRepository.save(recycleForm);
        return mapToRecycleFormResponseDto(savedForm);
    }

    public List<RecycleFormResponseDto> getAllOrders(String userId) {


        return recycleFormRepository.findByOrganisation_OrganisationIdAndStatus(userId, false).stream()
                .map(this::mapToRecycleFormResponseDto)
                .collect(Collectors.toList());
    }

    public RecycleFormResponseDto getOrderDetail(String orderId) {
        RecycleForm recycleForm = recycleFormRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        RecycleFormResponseDto response = mapToRecycleFormResponseDto(recycleForm);

        return response;
    }

    public ApiResponseDto orderAccepted(String orderId) {
        RecycleForm recycleForm = recycleFormRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        recycleForm.setStatus(true);
        recycleFormRepository.save(recycleForm);

        String message = String.format(
                "Your request has been accepted by %s. Scrap pickup will be soon.\nScrap collector: %s",
                recycleForm.getOrganisation().getOrganisationName(),
                recycleForm.getOrganisation().getPhone()

        );

        notificationService.createNotification(recycleForm.getUser(), message,true);
        return new ApiResponseDto(true, "Order accepted");
    }

    public ApiResponseDto orderRejected(String orderId, String reason) {
        RecycleForm recycleForm = recycleFormRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        String message = String.format(
                "Your request has been rejected by %s. Scrap collector said '%s'",
                recycleForm.getOrganisation().getOrganisationName(),
                reason
        );

        notificationService.createNotification(recycleForm.getUser(), message,false);
        recycleFormRepository.delete(recycleForm);
        return new ApiResponseDto(false, "Order rejected successfully");
    }

    private RecycleFormResponseDto mapToRecycleFormResponseDto(RecycleForm recycleForm) {
        RecycleFormResponseDto dto = new RecycleFormResponseDto();
        dto.setOrderId(recycleForm.getOrderId());
        dto.setUser(recycleForm.getUser());
        dto.setOrganisationId(recycleForm.getOrganisation().getid());
        dto.setItemType(recycleForm.getItemType());
        dto.setDate(recycleForm.getDate());
        dto.setPhone(recycleForm.getPhone());
        dto.setImagePath(recycleForm.getImagePath());
        dto.setWeight(recycleForm.getWeight());
        dto.setLongitude(recycleForm.getLongitude());
        dto.setLatitude(recycleForm.getLatitude());
        dto.setDate(recycleForm.getDate());

        return dto;
    }
}