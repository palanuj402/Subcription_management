Subscription Management System – Microservices Architecture
🚀 Overview
This project is a production-style microservices-based Subscription Management System built using Java 17 and Spring Boot.
The system manages:
User creation
Subscription management
Billing & payment processing
The architecture follows clean layering principles and inter-service communication using REST APIs.
🏗 Architecture
The system consists of 3 independent microservices:
1️⃣ User Service
Create and fetch users
Stores user data in MySQL
2️⃣ Subscription Service
Creates subscriptions for users
Validates user and plan
Calls Billing Service for payment processing
Stores subscription status
3️⃣ Billing Service
Processes subscription payments
Returns SUCCESS / FAILED status
Simulates real-world payment handling
🔁 System Flow
User → Subscription Service → Billing Service → Response
User is created
Subscription is requested
Subscription service validates user
Billing service processes payment
Subscription status is updated
🛠 Tech Stack
Java 17
Spring Boot
Spring Data JPA
MySQL
REST APIs
Lombok
Maven
Postman (API testing)
📂 Project Structure
subscription_management
│
├── User-Service
├── Subscription-Service
├── Billing-Service
Each service contains:
Controller layer
Service layer
Repository layer
Entity & DTO
Exception handling
⚙️ Key Features
✔ Microservices architecture
✔ Inter-service communication
✔ Transaction management
✔ Global exception handling
✔ Clean code and SOLID principles
✔ End-to-end API testing
🧠 Learning Outcomes
Designed distributed microservices system
Implemented production-style service communication
Applied LLD concepts in real project
Structured layered architecture
Implemented proper exception handling
🔮 Future Enhancements
JWT-based authentication
API Gateway
Service Registry (Eureka)
Docker containerization
Circuit Breaker (Resilience4j)
Centralized logging
💡 How to Run
Start MySQL
Create required databases
Run services on different ports
Use Postman to test APIs
👨‍💻 Author
Anuj Pal
Java Backend Developer
