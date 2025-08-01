---

# Section 1:  Introduction to Microservices Architecture

---

**What will we build in the course? – Summary**
External traffic enters the microservices network through an API Gateway.

The API Gateway is secured using OpenID, OAuth2, and Keycloak.

**Service Discovery**: Spring Cloud Eureka.

**REST API**: Spring Boot with documentation and validation.

**Technologies used**: Spring Boot, Spring Cloud, Spring Security, Resilience4j, Kafka, RabbitMQ.

Synchronous and asynchronous communication.

**Monitoring and metrics**: Prometheus, Grafana, OpenTelemetry.

**Containerization**: Docker, Kubernetes, Helm.

**Microservices architecture inspired by the 15-Factor App.**

**Architectures**:
**Monolith**: Single application, single database, difficult to scale and deploy.
**SOA**: Loosely coupled services, but often share a database and middleware.
**Microservices**: Independent, separate databases, easy to scale and deploy, but with increased infrastructure complexity.

**Key characteristics of microservices**:

* Independent deployment and development.
* Modeled around business domains.
* Increased agility and scalability.
* **Challenges**: Security, complexity, infrastructure.

Microservices are an approach to building a single application as a set of small services, each running independently, with its own lightweight business process and mechanisms, fully automatable, autonomously deployable, and communicating with other services.
