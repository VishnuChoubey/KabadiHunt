package com.KabadiHunt.controllers;

import com.KabadiHunt.dto.NotificationDto;
import com.KabadiHunt.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<NotificationDto>> getNotifications(@RequestParam String userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUserId(userId));
    }
}
