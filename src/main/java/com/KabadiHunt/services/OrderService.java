//package com.ScrapBridge.services;
//
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import com.ScrapBridge.models.Order;
//import com.ScrapBridge.models.User;
//import com.ScrapBridge.repositories.OrderRepository;
//import com.ScrapBridge.repositories.UserRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//
//public class OrderService {
//    private final OrderRepository orderRepository;
//    private final UserRepository userRepository;
//    public OrderService(OrderRepository orderRepository,UserRepository userRepository){
//        this.orderRepository=orderRepository;
//        this.userRepository=userRepository;
//    }
//
//    public Order createOrder(String itemType, String location, String phone, String userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Order order = new Order();
//        order.setOrderId(generateOrderId());
//        order.setItemType(itemType);
//        order.setLocation(location);
//        order.setPhone(phone);
//        order.setStatus("pending");
//        order.setUser(user);
//        order.setCreatedAt(new Date());
//
//        return orderRepository.save(order);
//    }
//
//    public List<Order> getUserOrders(String userId) {
//        return orderRepository.findByUser_Id(userId);
//    }
//
//    public List<Order> getRecyclerOrders(String recyclerId) {
//        return orderRepository.findByRecycler_Id(recyclerId);
//    }
//
//    public List<Order> getPendingPaymentsForRecycler(String recyclerId) {
//        return orderRepository.findByRecycler_IdAndStatus(recyclerId, "completed");
//    }
//
//    public Optional<Order> getOrderById(String orderId) {
//        return orderRepository.findById(orderId);
//    }
//
//    public Order assignRecyclerToOrder(String orderId, String recyclerId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        User recycler = userRepository.findById(recyclerId)
//                .orElseThrow(() -> new RuntimeException("Recycler not found"));
//
//        order.setRecycler(recycler);
//        return orderRepository.save(order);
//    }
//
//    public Order updateOrderStatus(String orderId, String status) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        order.setStatus(status);
//        return orderRepository.save(order);
//    }
//
//    private String generateOrderId() {
//        return "ORD" + System.currentTimeMillis();
//    }
//}