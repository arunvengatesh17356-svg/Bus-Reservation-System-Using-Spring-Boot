package com.example.BusReservationSystem.Controller;

import com.example.BusReservationSystem.Entity.Booking;
import com.example.BusReservationSystem.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @GetMapping
    public String welcome() {
        return "Welcome to Bus Booking System ";
    }
    @PostMapping("/book")
    public String book(@RequestBody Booking booking) {
        return bookingService.bookTicket(booking);
    }

    @DeleteMapping("/cancel/{id}")
    public String cancel(@PathVariable int id) {
        return bookingService.cancelTicket(id);
    }
}
