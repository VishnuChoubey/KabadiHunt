package com.KabadiHunt.services;




import com.KabadiHunt.dto.NotificationDto;
import com.KabadiHunt.exception.ResourceNotFoundException;
import com.KabadiHunt.models.Notification;
import com.KabadiHunt.models.User;
import com.KabadiHunt.repositories.NotificationRepository;
import com.KabadiHunt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    public NotificationDto createNotification(User user, String message,boolean status) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setStatus(status);
        notification.setMessage(message);
        Notification savedNotification = notificationRepository.save(notification);
        return mapToNotificationDto(savedNotification);
    }

    public List<NotificationDto> getNotificationsByUserId(String userId) {
        User endUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("EndUser not found with id: " + userId));

        return notificationRepository.findByUser_IdOrderByCreatedDesc(endUser.getId()).stream()
                .map(this::mapToNotificationDto)
                .collect(Collectors.toList());
    }

    public List<NotificationDto> getNotificationsByUser(String user) {
        User endUser = userRepository.findById(user)
                .orElseThrow(() -> new ResourceNotFoundException("EndUser not found for user: "));

        return notificationRepository.findByUser_IdOrderByCreatedDesc(endUser.getId()).stream()
                .map(this::mapToNotificationDto)
                .collect(Collectors.toList());
    }

    private NotificationDto mapToNotificationDto(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notification.getId());
        dto.setUser(notification.getUser());
        dto.setStatus(notification.getStatus());
        dto.setOwner(notification.getOwner());
        dto.setMessage(notification.getMessage());
        dto.setCreated(notification.getCreated());
        return dto;
    }
}