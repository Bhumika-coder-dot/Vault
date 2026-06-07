# 🔐 Vault - API Credential Expiry Management System

Vault is a Spring Boot-based backend system designed to securely store API credentials and automatically monitor their expiry.  
It sends email alerts before credentials expire, helping developers and teams avoid service disruptions.

---

## 🚀 Features

- 🔐 Secure storage of API credentials
- 📧 Automated email alerts for expiring credentials
- ⏰ Scheduler-based expiry monitoring (runs daily at 9 AM)
- 📅 Alerts sent 7, 3, and 1 day before expiry
- 🧠 JWT Authentication & Authorization (Role-based access)
- 👤 User management system
- 🌐 RESTful APIs using Spring Boot
- 🗄️ MongoDB integration for data storage

---

## 🏗️ Tech Stack

- Java 17+
- Spring Boot
- Spring Security (JWT)
- Spring Data MongoDB
- Maven
- Java Mail Sender
- Lombok

---

## 📁 Project Structure


com.bhumika.vault
│
├── config # Security & application configuration
├── controller # REST APIs
├── entity # MongoDB models
├── repository # Data access layer
├── service # Business logic
├── scheduler # Expiry email scheduler
├── filter # JWT authentication filter
├── utils # Helper utilities (JWT, etc.)
└── VaultApplication # Main Spring Boot application


---

## ⏰ Scheduler Logic

- Runs daily at **9:00 AM**
- Fetches all vault entries
- Checks expiry dates
- Sends email alerts when:
  - 7 days remaining
  - 3 days remaining
  - 1 day remaining

---

## 🔐 Security

- JWT-based authentication
- Role-based access control (USER / ADMIN)
- Secure endpoints for vault operations
- Public endpoints for signup/login

---

## 📦 API Endpoints (Sample)

### Public APIs

POST /public/register
POST /public/login


### User APIs

GET /vault/getAll
POST /vault/add
DELETE /vault/delete/{id}


### Admin APIs

GET /admin/dashboard


---

## ⚙️ How to Run Locally

### 1. Clone the repository

bash
git clone https://github.com/Bhumika-coder-dot/Vault.git
2. Configure MongoDB

Update application.properties:

spring.data.mongodb.uri=your_mongodb_uri
3. Configure Email Service

Set your email credentials:

spring.mail.username=your_email
spring.mail.password=your_password


4. Run the application
mvn spring-boot:run
📬 Future Enhancements
Redis caching for performance optimization
Kafka-based event processing
Frontend (React dashboard)
Docker containerization
Cloud deployment (AWS / Render / VPS)


👩‍💻 Author

Bhumika Kolekar

GitHub: Bhumika-coder-dot
