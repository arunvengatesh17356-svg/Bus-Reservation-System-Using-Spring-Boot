package com.example.BusReservationSystem.Repository;

import com.example.BusReservationSystem.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {}
