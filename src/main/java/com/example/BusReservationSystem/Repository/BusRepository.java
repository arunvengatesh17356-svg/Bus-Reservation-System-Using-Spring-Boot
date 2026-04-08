package com.example.BusReservationSystem.Repository;

import com.example.BusReservationSystem.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer> {

}
