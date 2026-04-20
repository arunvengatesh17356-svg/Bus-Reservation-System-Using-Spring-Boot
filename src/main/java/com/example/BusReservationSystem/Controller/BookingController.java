package com.example.BusReservationSystem.Controller;

import com.example.BusReservationSystem.Entity.Booking;
import com.example.BusReservationSystem.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/home")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String home() {
        return "Welcome to Bus Booking System ";
    }
    @PostMapping("/book")
    public String book(@RequestBody Booking booking) {
        return bookingService.bookTicket(booking);
    }
    @GetMapping("/history/{phone}")
    public List<Booking> getHistory(@PathVariable("phone") String phone) {
        return bookingService.getBookingHistory(phone);
    }
    @PutMapping("/update-boarding/{id}")
    public String updateBoarding(@PathVariable int id, @RequestParam String boardingPoint) {

        return bookingService.updateBoardingPoint(id, boardingPoint);
    }
    @DeleteMapping("/cancel/{id}")
    public String cancel(@PathVariable int id) {
        return bookingService.cancelTicket(id);
    }
}
