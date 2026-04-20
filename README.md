# 🚌 Bus Reservation System (Spring Boot)

## 📌 Overview
This is a backend-based Bus Reservation System built using Java and Spring Boot.  
It provides REST APIs to handle booking, cancellation, payment processing, and travel reminders.

---

## 🚀 Features
- 🎟️ Book bus tickets  
- 📄 View booking history  
- ✏️ Update boarding point  
- ❌ Cancel bookings  
- 💳 Payment processing (Card / UPI / Cash)  
- ⏰ Automated reminders using Scheduler  
- 🚫 Prevent duplicate seat booking  

---

## 🛠️ Technologies Used
- Java  
- Spring Boot  
- Spring Data JPA  
- MySQL  
- Maven  
- Lombok  
- Postman  

---

src/main/java/com/example/BusReservationSystem/

├── Controller
│ └── BookingController.java

├── Entity
│ ├── Booking.java
│ ├── Bus.java
│ └── Payment.java

├── Repository
│ ├── BookingRepository.java
│ ├── BusRepository.java
│ └── PaymentRepository.java

├── Service
│ ├── BookingService.java
│ ├── PaymentService.java
│ └── ReminderService.java

└── BusReservationSystemApplication.java


---

## ⚙️ Setup & Installation

### 1. Clone Repository
```bash
git clone https://github.com/your-username/bus-reservation-system.git
cd bus-reservation-system

Run Application
mvn spring-boot:run

🏠 Home
GET /home

🎟️ Book Ticket
POST /home/book

Sample Request
{
  "bookingId": 1,
  "passengerName": "Arun",
  "age": 22,
  "gender": "Male",
  "passengerNumber": "9876543210",
  "busNo": 101,
  "boardingPoint": "Chennai",
  "droppingPoint": "Bangalore",
  "date": "25-04-2026",
  "seatNo": 5,
  "travelTime": "10:30:00",
  "method": "UPI"
}
📄 Booking History
GET /home/history/{phone}

✏️ Update Boarding Point
PUT /home/update-boarding/{id}?boardingPoint=NewLocation

❌ Cancel Ticket
DELETE /home/cancel/{id}

💳 Payment Logic
CARD → SUCCESS
UPI → SUCCESS
CASH → SUCCESS
Others → FAILED

⏰ Reminder System
Runs every 1 second
Sends:
Reminder at travel time
Reminder 1 hour before travel
Uses in-memory cache for fast processing

🧠 Business Logic
Validates seat availability
Prevents duplicate booking
Auto processes payment
Auto schedules reminders

🧪 Testing

Use Postman to test APIs.

⭐ Future Improvements
JWT Authentication
Frontend Integration
Email/SMS Notifications
Admin Dashboard

👨‍💻 Author

Arun Vengatesh






## 📂 Project Structure
