package com.example.BusReservationSystem.Service;

import com.example.BusReservationSystem.Entity.Payment;
import com.example.BusReservationSystem.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    public void processPayment(int bookingId, double amount,String method) {

        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setMethod(method);
        if (method.equalsIgnoreCase("CARD")) {
            payment.setStatus("SUCCESS");
        } else if (method.equalsIgnoreCase("UPI")) {
            payment.setStatus("SUCCESS");
        } else if (method.equalsIgnoreCase("CASH")) {
            payment.setStatus("PENDING");
        } else {
            payment.setStatus("FAILED");
        }
        paymentRepo.save(payment);
    }
}