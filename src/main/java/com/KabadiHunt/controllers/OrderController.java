//package com.ScrapBridge.controllers;
//
//
//import com.ScrapBridge.dto.ApiResponseDto;
//import com.ScrapBridge.dto.OrderStatusDto;
//import com.ScrapBridge.services.OrderService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/order")
//public class OrderController {
//
//    private final OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @PostMapping("/scrap-request")
//    public ResponseEntity<OrderStatusDto> submitScrapRequest(@RequestBody ScrapRequestDto scrapRequest) {
//        return ResponseEntity.ok(orderService.submitScrapRequest(scrapRequest));
//    }
//
//    @GetMapping("/details/{user_id}")
//    public ResponseEntity<List<OrderStatusDto>> getAllOrders(@PathVariable("user_id") String userId) {
//        return ResponseEntity.ok(orderService.getAllOrders(userId));
//    }
//
//    @GetMapping("/detail/{order_id}")
//    public ResponseEntity<OrderStatusDto> getOrderDetail(@PathVariable("order_id") String orderId) {
//        return ResponseEntity.ok(orderService.getOrderDetail(orderId));
//    }
//
//    @GetMapping("/pending/{user_id}")
//    public ResponseEntity<List<OrderStatusDto>> getAllPendingOrders(@PathVariable("user_id") String userId) {
//        return ResponseEntity.ok(orderService.getAllPendingOrders(userId));
//    }
//
//    @PutMapping("/accept/{order_id}")
//    public ResponseEntity<ApiResponseDto> orderAccepted(@PathVariable("order_id") String orderId) {
//        return ResponseEntity.ok(orderService.orderAccepted(orderId));
//    }
//
//    @PostMapping("/reject/{order_id}")
//    public ResponseEntity<ApiResponseDto> orderRejected(
//            @PathVariable("order_id") String orderId,
//            @RequestBody RejectOrderRequest rejectRequest) {
//        return ResponseEntity.ok(orderService.orderRejected(orderId, rejectRequest.getReason()));
//    }
//}