package com.example.BusReservationSystem.Repository;

import com.example.BusReservationSystem.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    int countByBusNoAndDate(int busNo, LocalDate date);
    boolean existsByBusNoAndDateAndSeatNo(int busNo, LocalDate date, int seatNo);
}