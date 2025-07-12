---

This section was the first hands-on part of the course [Master Microservices with Spring, Docker & Kubernetes](https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes/?couponCode=ST17MT70725A).

---

---

# Section 2: First Hands-On Microservices Project

## Building foundational microservices with Spring Boot, featuring full CRUD APIs, validation, error handling, and API documentation.


This section provides a theoretical introduction to Cloud Native concepts and principles without any coding examples. Cloud Native is an approach to building applications that fully leverage cloud computing by focusing on scalability, resilience, and efficient deployment using modern practices and tools.

---
### 📌 What I learned

#### 🔧 Microservices Fundamentals

* What microservices are and how to build them
* Practical comparison between monolithic and microservices architecture

#### 🌐 REST APIs

* Introduction to REST APIs and best practices for designing them
* Creating a Spring Boot project using IntelliJ IDEA

#### 🧱 Spring Data JPA and DTO

* Creating JPA entities and repositories using Spring Data
* Introduction to the DTO (Data Transfer Object) pattern
* Creating DTOs inside the accounts microservice

#### 🧾 Full REST CRUD Functionality

* Implemented the following API endpoints:

    * CREATE
    * READ
    * UPDATE
    * DELETE
* Handling exceptions globally (exception handling logic)
* Validating input data using annotations
* Automatically updating audit columns (e.g., created/modified dates)

#### 📘 Documenting APIs with OpenAPI (springdoc-openapi)

* Learned how to generate API documentation
* Used annotations like:

    * `@OpenAPIDefinition`
    * `@Tag`
    * `@Operation`
    * `@ApiResponse`
    * `@Schema` (with example data)

#### 🧪 Key REST Annotations and Classes

* Got familiar with the most commonly used annotations for building REST services in Spring Boot

#### 🏗️ Practical Project – Accounts, Loans & Cards Microservices

* Assignment to build two additional services: Loans and Cards
* In-depth walkthrough and demo of each service implementation

---

### 📁 Outcome of This Section

* I created three separate monolithic applications: **accounts**, **loans**, and **cards**
* Each service includes full CRUD REST APIs, input validation, error handling, and OpenAPI documentation
* In future sections, these services will be converted into true microservices and integrated with one another

---

### 🚀 Postman Collections

In the **postman** folder, you can find collections to test each of the three applications.

---

