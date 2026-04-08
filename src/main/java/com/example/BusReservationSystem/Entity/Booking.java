package com.example.BusReservationSystem.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"busNo", "date", "seatNo"}
        )
)
public class Booking {
    @Id
    private int bookingId;
    private String passengerName;
    private int age;
    private String gender;
    private String passengerNumber;
    private int busNo;
    private String boardingPoint;
    private String droppingPoint;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private int seatNo;
    private boolean notified = false;
    private LocalTime travelTime;
    private String Method;
}