package com.example.BusReservationSystem.Service;

import com.example.BusReservationSystem.Entity.Booking;
import com.example.BusReservationSystem.Repository.BookingRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReminderService {

    @Autowired
    private BookingRepository bookingRepo;

    private List<Booking> cachedBookings = new ArrayList<>();

    @PostConstruct
    public void loadData() {
        cachedBookings = bookingRepo.findAll();
        System.out.println("Bookings loaded: " + cachedBookings.size());
    }

    @Scheduled(fixedRate = 1000)
    public void checkReminder() {

        try {
            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();
            LocalTime nextTime = now.plusSeconds(5); // safe window

            for (Booking booking : cachedBookings) {

                try {
                    if (booking.getDate() == null ||
                            booking.getTravelTime() == null ||
                            booking.isNotified()) {
                        continue;
                    }

                    if (!booking.getDate().equals(today)) {
                        continue;
                    }

                    LocalTime travelTime = booking.getTravelTime();

                    if (!travelTime.isBefore(now) &&
                            travelTime.isBefore(nextTime)) {

                        System.err.println(" Reminder: Dear "
                                + booking.getPassengerName()
                                + ", your bus (Bus No: "
                                + booking.getBusNo()
                                + ") is scheduled at "
                                + travelTime);

                        booking.setNotified(true);

                        bookingRepo.save(booking);
                    }

                    LocalTime oneHourBefore = travelTime.minusHours(1);

                    if (!oneHourBefore.isBefore(now) &&
                            oneHourBefore.isBefore(nextTime) &&
                            !booking.isNotified()) {

                        System.out.println(" Reminder: Dear "
                                + booking.getPassengerName()
                                + ", your bus (Bus No: "
                                + booking.getBusNo()
                                + ") will start in 1 hour!");

                    }

                } catch (Exception e) {
                    System.out.println(" Error processing booking ID: "
                            + booking.getBookingId());
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println(" Scheduler failed!");
            e.printStackTrace();
        }
    }

    public void addToCache(Booking booking) {
        cachedBookings.add(booking);
    }

    public void removeFromCache(int id) {
        cachedBookings.removeIf(b -> b.getBookingId() == id);
    }
    public void updateCache(Booking updated) {
        for (int i = 0; i < cachedBookings.size(); i++) {
            if (cachedBookings.get(i).getBookingId() == updated.getBookingId()) {
                cachedBookings.set(i, updated);
                break;
            }
        }
    }
}