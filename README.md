# 🔐 Vault - API Credential Lifecycle & Expiry Management System

Vault is a cloud-deployed Spring Boot application designed to securely manage API credentials, track their expiration dates, and automatically notify users before credentials expire. The platform helps developers and organizations prevent service disruptions caused by expired API keys and secrets.

---

### 📖 Interactive API Documentation

* Swagger UI integration for testing APIs directly from the browser.
* Easy endpoint exploration and validation.


---

## 🌐 API Documentation

### Swagger UI

https://vault-byqy.onrender.com/swagger-ui/index.html

---
## 🚀 Key Features

### 🔐 Secure Credential Management

* Store and manage API credentials securely.
* Centralized credential lifecycle tracking.
* CRUD operations for credential management.

### 🔑 JWT Authentication & Authorization

* Secure user registration and login.
* JWT-based stateless authentication.
* Role-based access control (USER / ADMIN).

### 📧 Automated Email Notifications

* Automatic email alerts before credential expiration.
* Prevents unexpected API failures caused by expired credentials.

### ⏰ Scheduled Expiry Monitoring

* Daily scheduler checks credential expiry status.
* Sends alerts:

  * 7 Days Before Expiry
  * 3 Days Before Expiry
  * 1 Day Before Expiry

### ☁️ Cloud Deployment

* Dockerized Spring Boot application.
* Deployed on Render.
* MongoDB Atlas cloud database integration.
* Environment variable-based configuration management.


## 🏗️ System Architecture

```text
User
  │
  ▼
JWT Authentication
  │
  ▼
Spring Boot REST APIs
  │
  ▼
Business Services
  │
  ▼
MongoDB Atlas

Scheduler
  │
  ▼
Expiry Detection
  │
  ▼
Email Notification Service
```

---

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data MongoDB
* Maven

### Security

* JWT Authentication
* BCrypt Password Encryption
* Role-Based Authorization

### Database

* MongoDB Atlas

### Documentation

* Swagger / OpenAPI

### Deployment

* Docker
* Render

### Notification Service

* Java Mail Sender

### Development Tools

* Git
* GitHub
* Postman
* IntelliJ IDEA

---

## 📂 Project Structure

```text
com.bhumika.vault
│
├── config
├── controller
├── entity
├── repository
├── service
├── scheduler
├── filter
├── utils
└── VaultApplication
```

---

## 🔐 Security Features

* JWT-Based Authentication
* Role-Based Access Control
* BCrypt Password Encryption
* Stateless Session Management
* Protected REST Endpoints

---

## ⏰ Scheduler Workflow

The scheduler executes automatically every day and performs the following operations:

1. Fetches all stored credentials.
2. Calculates remaining days until expiration.
3. Identifies credentials nearing expiry.
4. Sends automated email notifications.
5. Helps prevent service interruptions caused by expired credentials.

---

## 📦 Core API Endpoints

### Public APIs

```http
POST /public/signup
POST /public/login
```

### User APIs

```http
GET /vault/getAll
POST /vault/add
DELETE /vault/delete/{id}
```

### Admin APIs

```http
GET /admin/dashboard
```

---

## ⚙️ Local Setup

### Clone Repository

```bash
git clone https://github.com/Bhumika-coder-dot/Vault.git
cd Vault
```

### Configure Environment Variables

```env
MONGO_URI=your_mongodb_uri
MAIL_USERNAME=your_email
MAIL_PASSWORD=your_app_password
WEATHER_API_KEY=your_weather_api_key
```

### Run Application

```bash
mvn spring-boot:run
```

---

## 🎯 Project Highlights

* Designed and developed a secure Spring Boot backend from scratch.
* Implemented JWT-based authentication and authorization.
* Integrated MongoDB Atlas for cloud-based data storage.
* Developed scheduler-driven automated email notifications.
* Dockerized and deployed the application on Render.
* Configured environment-based secret management for production deployment.
* Integrated Swagger UI for API documentation and testing.

---

## 📈 Future Enhancements

* React Frontend Dashboard
* Credential Encryption using AES
* Audit Logging
* Redis Caching
* Team Collaboration Features
* Analytics Dashboard
* Kafka-Based Event Notifications

---

## 👩‍💻 Author

### Bhumika Kolekar

Computer Engineering Student | Java Backend Developer

**GitHub:**
https://github.com/Bhumika-coder-dot

**LinkedIn:**
https://www.linkedin.com/in/bhumika-kolekar-a88412323/

---

⭐ If you found this project useful, consider giving it a star.
