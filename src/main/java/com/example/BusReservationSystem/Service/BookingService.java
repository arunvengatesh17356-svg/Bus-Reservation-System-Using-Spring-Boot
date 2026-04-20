package com.example.BusReservationSystem.Service;

import com.example.BusReservationSystem.Entity.Booking;
import com.example.BusReservationSystem.Entity.Bus;
import com.example.BusReservationSystem.Repository.BookingRepository;
import com.example.BusReservationSystem.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepo;
    @Autowired
    private BusRepository busRepo;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ReminderService reminderService;
    public String bookTicket(Booking booking) {

        if (booking.getDate() == null) {
            return "Travel date required";
        }

        Bus bus = busRepo.findById(booking.getBusNo()).orElse(null);

        if (bus == null) {
            return "Bus Not Found";
        }

        if (booking.getSeatNo() > bus.getCapacity() || booking.getSeatNo() <= 0) {
            return "Invalid Seat Number";
        }

        boolean seatExists = bookingRepo.existsByBusNoAndDateAndSeatNo(booking.getBusNo(), booking.getDate(), booking.getSeatNo());

        if (seatExists) {
            return "Seat already booked!";
        }

        bookingRepo.save(booking);

        reminderService.addToCache(booking);

        paymentService.processPayment(booking.getBookingId(), bus.getTicketPrice(), booking.getMethod());

        return "Booking Successful";
    }
    public List<Booking> getBookingHistory(String passengerNumber) {
        return bookingRepo.findByPassengerNumber(passengerNumber);
    }
    public String updateBoardingPoint(int bookingId, String newBoardingPoint) {

        Booking booking = bookingRepo.findById(bookingId).orElse(null);

        if (booking == null) {
            return "Booking Not Found";
        }

        booking.setBoardingPoint(newBoardingPoint);

        bookingRepo.save(booking);
        reminderService.updateCache(booking);

        return "Boarding Point Updated Successfully";
    }
    public String cancelTicket(int bookingId) {

        if (!bookingRepo.existsById(bookingId)) {
            return "Booking Not Found ";
        }

        bookingRepo.deleteById(bookingId);
        reminderService.removeFromCache(bookingId);
        return "Cancelled Successfully ";
    }
}
