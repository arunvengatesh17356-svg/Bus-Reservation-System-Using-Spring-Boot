package com.example.BusReservationSystem.Service;

import com.example.BusReservationSystem.Entity.Booking;
import com.example.BusReservationSystem.Entity.Bus;
import com.example.BusReservationSystem.Repository.BookingRepository;
import com.example.BusReservationSystem.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

        boolean seatExists = bookingRepo.existsByBusNoAndDateAndSeatNo(
                booking.getBusNo(),
                booking.getDate(),
                booking.getSeatNo()
        );

        if (seatExists) {
            return "Seat already booked!";
        }

        int booked = bookingRepo.countByBusNoAndDate(
                booking.getBusNo(),
                booking.getDate()
        );

        if (booked >= bus.getCapacity()) {
            return "Bus Full";
        }

        bookingRepo.save(booking);

        reminderService.addToCache(booking);

        paymentService.processPayment(
                booking.getBookingId(),
                bus.getTicketPrice(),
                booking.getMethod()
        );

        return "Booking Successful";
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