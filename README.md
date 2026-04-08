# 🚌 Bus Reservation System using Spring Boot

📌 Overview  
This is a backend-based Bus Reservation System developed using Java and Spring Boot.  
The system allows users to book bus tickets, manage bookings, and perform operations using REST APIs.

---

🚀 Features  
Book bus tickets  
View booking details  
Delete bookings  
REST API implementation  
Date and time handling using Java 8  

---

🛠️ Technologies Used  
Java  
Spring Boot  
Spring Web  
JDBC  
MySQL  
Maven  
Postman  

---

📂 Project Structure  
BookingController.java  
BookingService.java  
BookingRepository.java  
Booking.java  
JacksonConfig.java  

---

📡 API Endpoints  

➤ Book Ticket  
POST /api/book  

➤ Delete Booking  
DELETE /api/delete/{id}  

➤ Welcome API  
GET /api  

---

🧾 Database Table  

Booking Table  

CREATE TABLE booking (  
    booking_id INT PRIMARY KEY,  
    passenger_name VARCHAR(50),  
    age INT,  
    gender VARCHAR(10),  
    bus_no VARCHAR(20),  
    travel_date DATE,  
    travel_time TIME  
);  

---

🔄 Program Flow  
User sends booking request using API  
Data is processed in service layer  
Booking is stored in database  
User can delete booking using ID  

---

⚙️ How to Run  

Clone the project  
Open in IDE (IntelliJ / Eclipse)  
Configure MySQL database  
Update application.properties  
Run Spring Boot application  
Test APIs using Postman  

---

👨‍💻 Author  

Arun Vengatesh  
