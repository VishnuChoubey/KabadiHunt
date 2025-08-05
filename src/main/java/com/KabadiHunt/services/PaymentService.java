//package com.KabadiHunt.services;
//
//
//
//import com.KabadiHunt.dto.PaymentRequestDto;
//import com.KabadiHunt.dto.PaymentResponseDto;
//import com.KabadiHunt.repositories.OwnerRepository;
//import com.KabadiHunt.repositories.PaymentRepository;
//import com.KabadiHunt.repositories.UserRepository;
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class PaymentService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private OwnerRepository ownerRepository;
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    @Value("${razorpay.key-id}")
//    private String razorpayKeyId;
//
//    public String getRazorpayKeyId() {
//        return razorpayKeyId;
//    }
//    public PaymentResponseDto createPayment(PaymentRequestDto requestDto) {
//        try {
//            RazorpayClient razorpay = new RazorpayClient("rzp_test_XXXXXXX", "your_secret_key");
//
//            JSONObject options = new JSONObject();
//            options.put("amount", requestDto.getAmount().multiply(BigDecimal.valueOf(100)).intValue()); // amount in paise
//            options.put("currency", requestDto.getCurrency());
//            options.put("receipt", "order_rcptid_" + System.currentTimeMillis());
//            options.put("payment_capture", 1); // automatic capture
//
//            Order order = razorpay.orders.create(options);
//
//            PaymentResponseDto response = new PaymentResponseDto();
//            response.setPaymentId(order.get("id")); // Razorpay Order ID
//            response.setAmount(requestDto.getAmount());
//            response.setCurrency(requestDto.getCurrency());
//            response.setApiKey("rzp_test_XXXXXXX"); // Razorpay public key for frontend
//
//            return response;
//
//        } catch (RazorpayException e) {
//            throw new RuntimeException("Failed to create Razorpay order", e);
//        }
//    }
//
//    @Transactional
//    public ApiResponseDto processPaymentStatus(String orderId, String userId, String ownerId,
//                                               BigDecimal amount, String transactionId) {
//        try {
//            Optional<Payments> existing = paymentRepository.findByTransactionId(transactionId);
//            if (existing.isPresent()) {
//                return new ApiResponseDto(false, "Transaction already exists.");
//            }
//
//            User user = userRepository.findById(userId)
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            Owner owner = ownerRepository.findById(ownerId)
//                    .orElseThrow(() -> new RuntimeException("Owner not found"));
//
//            Payments payment = new Payments();
//            payment.setTransactionId(transactionId);
//            payment.setAmount(amount);
//            payment.setCreated(LocalDateTime.now());
//            payment.setUser(userId);
//            payment.setOwner(ownerId);
//
//            paymentRepository.save(payment);
//
//            return new ApiResponseDto(true, "Payment recorded successfully.");
//        } catch (Exception e) {
//            return new ApiResponseDto(false, "Error processing payment: " + e.getMessage());
//        }
//    }
//
//    public void savePayment(String userId, String ownerId, String transactionId, BigDecimal amount) {
//        User endUserEntity = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Owner owner = ownerRepository.findById(ownerId)
//                .orElseThrow(() -> new RuntimeException("Owner not found"));
//
//        Payments payment = new Payments();
//        payment.setUser(userId);
//        payment.setOwner(ownerId);
//        payment.setTransactionId(transactionId);
//        payment.setAmount(amount);
//        payment.setCreated(LocalDateTime.now());
//
//        paymentRepository.save(payment);
//    }
//
//    public List<PaymentResponseDto> getSuccessfulPayments(String userId) {
//        List<Payments> payments = paymentRepository.findByUserId(userId);
//        return payments.stream()
//                .map(this::mapToPaymentResponse)
//                .collect(Collectors.toList());
//    }
//
//    private PaymentResponseDto mapToPaymentResponse(Payments payment) {
//        PaymentResponseDto response = new PaymentResponseDto();
//        response.setPaymentId(payment.getId());
//        response.setAmount(payment.getAmount());
//        response.setCurrency(payment.getCurrency());
//
//
//
//        return response;
//    }
//}
