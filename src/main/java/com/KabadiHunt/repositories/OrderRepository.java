//package com.ScrapBridge.repositories;
//
//
//
//import java.util.List;
//import java.util.Optional;
//
//import com.ScrapBridge.models.Order;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//
//public interface OrderRepository extends MongoRepository<Order, String> {
//    List<Order> findByUser_Id(String userId);
//    List<Order> findByRecycler_Id(String recyclerId);
//    List<Order> findByRecycler_IdAndStatus(String recyclerId, String status);
//    Optional<Order> findByOrderId(String orderId);
//}