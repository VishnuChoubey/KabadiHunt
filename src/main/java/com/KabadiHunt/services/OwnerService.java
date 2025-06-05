package com.KabadiHunt.services;

import com.KabadiHunt.dto.OwnerDto;

import com.KabadiHunt.models.Owner;
import com.KabadiHunt.models.User;
import com.KabadiHunt.repositories.OwnerRepository;
import com.KabadiHunt.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    public OwnerService(OwnerRepository ownerRepository,UserRepository userRepository) {
        this.ownerRepository = ownerRepository;
        this.userRepository=userRepository;
    }


    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    public List<OwnerDto> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    public OwnerDto getOwnerDetails(String userId) {
        Owner owner = ownerRepository.findByOrganisationId(userId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        return mapToDto(owner);
    }

    public OwnerDto updateOwner(String organisationId,String email, String phone, String street, String city, String state, String zipcode,Double lat,Double lon,String organiationName, String image) {
        User user = userRepository.findById(organisationId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
         Owner owner=new Owner();
         owner.setOrganisationId(organisationId);
        owner.setEmail(email);
        owner.setPhone(phone);
        owner.setStreet(street);
        owner.setCity(city);
        owner.setState(state);
        owner.setZipcode(zipcode);
        owner.setLatitude(lat);
        owner.setLongitude(lon);
        owner.setOrganisationName(organiationName);
        owner.setCreatedAt(user.getCreatedAt());
        owner.setImagePath(image);

//        if (image != null && !image.isEmpty()) {
//            try {
//                owner.setImage(image.getBytes());
//                owner.setImageName(image.getOriginalFilename());
//                owner.setImageType(image.getContentType());
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to upload image", e);
//            }
//        }
        user.setProfileImageUrl(image);
        userRepository.save(user);
        ownerRepository.save(owner);
        return mapToDto(owner);
    }

    private OwnerDto mapToDto(Owner owner) {
        OwnerDto dto = new OwnerDto();
        dto.setOrganisationId(owner.getid());
        dto.setOrganisationName(owner.getOrganisationName());
        dto.setImagePath(owner.getImagePath());
        dto.setPhone(owner.getPhone());
        dto.setStreet(owner.getStreet());
        dto.setCity(owner.getCity());
        dto.setState(owner.getState());
        dto.setZipcode(owner.getZipcode());
        dto.setLatitude(owner.getLatitude());
        dto.setLongitude(owner.getLongitude());
        dto.setCreatedAt(owner.getCreatedAt());
        dto.setImagePath(owner.getImagePath());
        return dto;
    }
}
