# 📚 Library Management System (Spring Boot + JWT + MySQL)

A complete **Library Management System** built using **Spring Boot**,
secured with **JWT Authentication**, and backed by **MySQL**.\
This project manages **Users, Books, Borrow Records, and Reservations**,
exposing a structured REST API fully tested using Postman.

------------------------------------------------------------------------

## 🏷️ Tech Stack

![Java](https://img.shields.io/badge/Java-17-blue)\
![Spring
Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)\
![JWT](https://img.shields.io/badge/JWT-Security-orange)\
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)\
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-purple)\
![Maven](https://img.shields.io/badge/Build-Maven-yellow)

------------------------------------------------------------------------

# 🚀 Features

### 🔐 Authentication & Authorization

-   JWT-based authentication
-   Login & Signup APIs
-   Role support (ADMIN, MEMBER)
-   Protected routes

### 👤 User Management

-   CRUD operations for users
-   Fetch user by email
-   Update user role

### 📘 Book Management

-   Add and manage books
-   Search by ID, ISBN, genre, author
-   Update and delete books

### 📖 Borrow Records

-   Issue and return books
-   Track due & return dates
-   Manage fines
-   Update or delete records

### 📝 Reservations

-   Reserve books
-   Modify or cancel reservations
-   View reservation details

------------------------------------------------------------------------

# 🧱 System Architecture

    ┌──────────────────────────────────────────────────────┐
    │                      Client App                       │
    │     (Postman / React / Angular / Mobile App)          │
    └──────────────────────────────────────────────────────┘
                                 |
                                 ▼
    ┌──────────────────────────────────────────────────────┐
    │                   Spring Boot API                     │
    │  Controllers → Services → Repositories → MySQL        │
    │                ↑                   ↓                  │
    │           JWTAuthFilter ← SecurityConfig              │
    └──────────────────────────────────────────────────────┘

------------------------------------------------------------------------

# 🗄️ Database ER Diagram

            ┌───────────────┐         ┌────────────────┐
            │     USERS      │ 1     * │ BORROW_RECORDS │
            │ id (PK)        │────────▶│ id (PK)        │
            │ name           │         │ user_id (FK)   │
            │ email          │         │ book_id (FK)   │
            │ password       │         │ issue_date     │
            │ role           │         │ due_date       │
            └───────────────┘         └────────────────┘
                    ▲                          ▲
                    │ 1                        │ *
                    │                          │
            ┌───────────────┐         ┌────────────────┐
            │     BOOKS      │ 1     * │ RESERVATIONS   │
            │ id (PK)        │────────▶│ id (PK)        │
            │ title          │         │ user_id (FK)   │
            │ isbn           │         │ book_id (FK)   │
            │ author         │         │ reservationDate│
            └───────────────┘         └────────────────┘

------------------------------------------------------------------------
## 🔒 User Roles & Permissions

The system implements **Role-Based Access Control (RBAC)** with three roles:

- **ADMIN**
- **LIBRARIAN**
- **MEMBER**

Below are the exact permissions based on the Spring Security configuration.

---

## 🛡️ ADMIN (Full Access)

Admins have unrestricted access across all resources.

### ✅ ADMIN Can:
- Add, update, and delete books  
- Add and update borrow records  
- Add and update reservations  
- Manage all users (update, delete, view)  
- Access all GET endpoints  
- Delete any resource (`DELETE /api/**`)  
- Create any resource (`POST /api/**`)  
- Update any resource (`PATCH /api/**`)  

---

## 📚 LIBRARIAN (Book & Record Manager)

Librarians can manage books, borrow records, and reservations, but **cannot modify users or delete anything**.

### ✅ LIBRARIAN Can:
- GET all books (`GET /api/book/**`)
- GET any resource (`GET /api/**`)
- Add books, borrow records, reservations (`POST /api/**`)
- Update books, borrow records, reservations (`PATCH /api/book`, `/api/borrowRecords`, `/api/reservation`)

### ❌ LIBRARIAN Cannot:
- Delete anything (`DELETE /api/**`)
- Update users (`PATCH /api/user/**`)
- Manage user accounts  

---

## 👤 MEMBER (Reader)

Members have view-only access to most resources except for books.

### ✅ MEMBER Can:
- View all books (`GET /api/book/**`)  

### ❌ MEMBER Cannot:
- Add, update, or delete books  
- Access GET `/api/**` for other modules  
- Post anything (`POST /api/**`)
- Update anything (`PATCH /api/**`)
- Delete anything (`DELETE /api/**`)

---

## 🔍 Summary Permissions Table

| Action | ADMIN | LIBRARIAN | MEMBER |
|--------|:-----:|:---------:|:------:|
| View Books (`GET /api/book/**`) | ✔️ | ✔️ | ✔️ |
| View Other Resources (`GET /api/**`) | ✔️ | ✔️ | ❌ |
| Create Resources (`POST /api/**`) | ✔️ | ✔️ | ❌ |
| Update Books/Records/Reservations (`PATCH /api/book`, `/api/borrowRecords`, `/api/reservation`) | ✔️ | ✔️ | ❌ |
| Update Users (`PATCH /api/user/**`) | ✔️ | ❌ | ❌ |
| Delete Any Resource (`DELETE /api/**`) | ✔️ | ❌ | ❌ |
| Authentication Routes (`/api/authenticate/**`) | ✔️ | ✔️ | ✔️ |

---

## 🔐 Notes  
- All roles must authenticate except for `/api/authenticate/**`.  
- JWT token is required for all protected routes.  



# 📌 API Documentation

## 🔐 Authentication APIs

| Method | Endpoint                     | Description           |
|--------|------------------------------|------------------------|
| POST   | /api/authenticate/signup     | Register new user     |
| POST   | /api/authenticate/login      | Login & generate JWT  |


## 👤 User APIs

| Method | Endpoint                      | Description          |
|--------|-------------------------------|----------------------|
| GET    | /api/user/{id}                | Get user by ID       |
| GET    | /api/user                     | Get all users        |
| GET    | /api/user/email/{email}       | Get user by email    |
| PATCH  | /api/user/{id}                | Modify user          |
| DELETE | /api/user/{id}                | Delete user          |


## 📘 Book APIs

| Method | Endpoint                          | Description        |
|--------|-----------------------------------|--------------------|
| GET    | /api/book/{id}                    | Get book by ID     |
| GET    | /api/book                         | Get all books      |
| GET    | /api/book/isbn/{isbn}             | Book by ISBN       |
| GET    | /api/book/category/{category}     | Books by genre     |
| GET    | /api/book/author/{author}         | Books by author    |
| POST   | /api/book                         | Add new book       |
| PATCH  | /api/book/{id}                    | Modify book        |
| DELETE | /api/book/{id}                    | Delete book        |


## 📖 Borrow Record APIs

| Method | Endpoint                          | Description           |
|--------|-----------------------------------|------------------------|
| GET    | /api/borrowRecords/{id}           | Get borrow record     |
| GET    | /api/borrowRecords                | Get all records       |
| POST   | /api/borrowRecords                | Add new record        |
| PATCH  | /api/borrowRecords/{id}           | Modify record         |
| DELETE | /api/borrowRecords/{id}           | Delete record         |


## 📝 Reservation APIs

| Method | Endpoint                          | Description           |
|--------|-----------------------------------|------------------------|
| GET    | /api/reservation/{id}             | Get reservation       |
| GET    | /api/reservation                  | Get all reservations  |
| POST   | /api/reservation                  | Add reservation       |
| PATCH  | /api/reservation/{id}             | Modify reservation    |
| DELETE | /api/reservation/{id}             | Delete reservation    |


------------------------------------------------------------------------

# 🛠️ Setup Instructions

## 1. Clone the Project

``` sh
git clone https://github.com/<your-username>/Library_Management_System.git
cd Library_Management_System
```

## 2. Configure MySQL

``` sql
CREATE DATABASE library_management;
```

Update `src/main/resources/application.properties`:

``` properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_management
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
```

## 3. Build the Project

``` sh
mvn clean install
```

## 4. Run the Application

``` sh
mvn spring-boot:run
```

Server: `http://localhost:8080`

------------------------------------------------------------------------

# 🔑 JWT Authentication

To store token in Postman:

``` js
pm.environment.set("authToken", pm.response.text());
```

Use in headers:

    Authorization: Bearer <token>

------------------------------------------------------------------------

# 📁 Project Structure

    src/main/java/com/Library_Management_System
    │
    ├── Controllers
    ├── DTOs
    ├── Entity
    ├── Repository
    ├── Service
    ├── Filters
    ├── Config
    └── Exception_Handling

------------------------------------------------------------------------

# 🧪 Postman Collection

Use the included file:

`Library_Mangement_System.postman_collection.json`

------------------------------------------------------------------------

