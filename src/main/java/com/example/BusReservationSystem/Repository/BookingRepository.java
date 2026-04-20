package com.example.BusReservationSystem.Repository;

import com.example.BusReservationSystem.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    boolean existsByBusNoAndDateAndSeatNo(int busNo, LocalDate date, int seatNo);
    List<Booking> findByPassengerNumber(String passengerNumber);
}
