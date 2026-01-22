![Java](https://img.shields.io/badge/Java-17-red?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.6-brightgreen?logo=springboot)
![Spring Security](https://img.shields.io/badge/Security-Spring%20Security-success?logo=springsecurity)
![JWT](https://img.shields.io/badge/Auth-JWT-orange?logo=jsonwebtokens)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue?logo=mysql)

![JPA](https://img.shields.io/badge/ORM-JPA%20Hibernate-informational)
![Resilience4j](https://img.shields.io/badge/Resilience-Resilience4j-lightgrey)
![Circuit Breaker](https://img.shields.io/badge/Pattern-Circuit%20Breaker-critical)
![Transactions](https://img.shields.io/badge/ACID-Transactional-success)


# FinFlow Wallet API

A backend digital wallet service built with Spring Boot demonstrating secure authentication, transactional integrity, and clean architectural design.





## About

FinFlow is a stateless REST API that supports user authentication, role-based authorization, and atomic wallet transfers. The project is designed to showcase backend engineering fundamentals expected in real-world financial systems.
## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- JWT (jjwt)
- Resilience4j
- JUnit / Spring Boot Test


## Features

- Stateless JWT authentication
- Role-based authorization (USER / ADMIN)
- Atomic wallet transfers using @Transactional
- Secure password hashing with BCrypt
- Integration-tested authentication flow
- Circuit breaker ready for external services
## API Reference



```http
Authentication
POST /api/auth/login 
```
Returns a signed JWT on successful authentication.


```http
Wallet
POST /api/wallet/transfer  
```
Transfers funds between users (transactional).

```http
Admin
GET /api/admin/users  
```
Lists all users (ADMIN only).


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`JWT_SECRET=secure-base64-secret`

`JWT_EXPIRATION_MS=3600000`

`DB_URL=jdbc:mysql://localhost:3306/finflow`

`DB_USERNAME=finflow_user`

`DB_PASSWORD=*****`


## Installation

Clone FinFLow with git

```bash
git clone https://github.com/your-username/finflow-wallet.git
cd finflow-wallet
./mvnw clean install
```
    
## Running Locally

Runs on http://localhost:8080, to run:

```bash
./mvnw spring-boot:run
```

Do not forget to clean and compile to remove `target` directories:

```bash
./mvnw clean
./mvnw compile
```

To test:

```bash
./mvnw test
```


## Security

- BCrypt password hashing
- Signed JWT tokens validated on every request
- Stateless authentication (no server-side sessions)
- Authorization enforced via Spring Security filter chain
## Authors

- [@Riad Mukhtarov](https://www.linkedin.com/in/riadmukhtarov/)


## License

[MIT](https://choosealicense.com/licenses/mit/)

