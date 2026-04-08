package com.example.BusReservationSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus")
public class Bus {

    @Id
    private int busNo;
    private  boolean ac;
    private  boolean Sleeper;
    private String Boarding_point;
    private String Dropping_point;
    private int capacity;
    private  int Travel_duration;
    private String driverName;
    private int driverNumber;
    private double ticketPrice;
    private LocalTime StatingTime;
    private LocalTime EndingTime;
}
