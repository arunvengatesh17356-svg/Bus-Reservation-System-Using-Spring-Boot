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

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String PINK = "\u001B[95m";
    public static final String CORAL = "\u001B[38;2;255;127;80m";
    public static final String SKY_BLUE = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";

    @PostConstruct
    public void loadData() {
        cachedBookings = bookingRepo.findAll();

        System.out.println(PINK + "Bookings loaded: " + cachedBookings.size() + RESET);
    }

    @Scheduled(fixedRate = 1000)
    public void checkReminder() {

        try {
            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();
            LocalTime nextTime = now.plusSeconds(5);

            for (Booking booking : cachedBookings) {

                try {
                    if (booking.getDate() == null || booking.getTravelTime() == null || booking.isNotified()) {
                        continue;
                    }

                    if (!booking.getDate().equals(today)) {
                        continue;
                    }

                    LocalTime travelTime = booking.getTravelTime();


                    if (!travelTime.isBefore(now) &&
                            travelTime.isBefore(nextTime)) {

                        System.out.println(SKY_BLUE+ "Reminder: Dear " + booking.getPassengerName() + ", your bus (Bus No: " + booking.getBusNo() + ") is scheduled at " + travelTime + RESET);

                        booking.setNotified(true);
                        bookingRepo.save(booking);
                    }

                    LocalTime oneHourBefore = travelTime.minusHours(1);

                    if (!oneHourBefore.isBefore(now) &&
                            oneHourBefore.isBefore(nextTime) &&
                            !booking.isNotified()) {

                        System.out.println(CORAL+ "Reminder: Dear " + booking.getPassengerName() + ", your bus (Bus No: " + booking.getBusNo() + ") will start in 1 hour!" + RESET);
                    }

                } catch (Exception e) {

                    System.out.println(YELLOW + "Error processing booking ID: " + booking.getBookingId() + RESET);
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println(RED + "Scheduler failed!" + RESET);
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
