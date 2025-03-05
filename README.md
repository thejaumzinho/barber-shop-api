# Barbershop API

## Overview
The **Barbershop API** is a RESTful web service built with **Java 21** and **Spring Boot**, designed to facilitate the management of a barbershop’s daily operations. It allows for efficient handling of customer records, appointment scheduling, and service pricing.

This project was developed as part of the **Decola Tech 2025 Bootcamp by Avanade**, focusing on best practices for backend development, database management, and containerization.

## Technologies Used
The project leverages modern backend technologies to ensure scalability, maintainability, and ease of deployment:

- **Java 17** – Core programming language
- **Spring Boot** – Framework for rapid backend development
    - Spring Web (REST API)
    - Spring Data JPA (Persistence Layer)
- **Gradle** – Build automation tool
- **PostgreSQL** – Relational database for data storage
- **Flyway** – Database version control and migrations
- **JPA/Hibernate** – ORM for database interaction
- **Docker** – Containerization for consistent deployment

## Features
The Barbershop API provides essential features for managing a barbershop, including:

- **Customer Management** – Create, update, retrieve, and delete customer records
- **Appointment Scheduling** – Manage customer appointments efficiently
- **Service Management** – Define barbershop services and pricing

### Prerequisites
Ensure you have the following installed:
- **Docker & Docker Compose** (for containerized setup)
- **Java 21**
- **Gradle**

### Running with Docker
To spin up the application using Docker, simply run:
```sh
docker-compose up
