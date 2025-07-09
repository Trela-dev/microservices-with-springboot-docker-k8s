

## 🌩️ Cloud Native — Theory & Principles

### 🔍 What is Cloud Native?

This is theoretical material for **Section 5*.
Cloud native is an approach to building and running applications that **maximizes the benefits of cloud computing**. These apps are **designed with the cloud in mind**.

---

### 🌟 Key Characteristics of Cloud Native Applications

* **Microservices**
  Apps are broken into small, independent services.

* **Containers**
  Run consistently across environments; cloud-agnostic.

* **Scalability & Flexibility**
  Horizontal scaling (e.g., via containers and orchestration tools like Kubernetes).

* **DevOps Practices**
  Close collaboration between developers and operations. Ensures smooth deployment cycles.

* **Resilience & Fault Tolerance**
  Features like load balancing and tools like `resilience4j` help ensure high availability.

* **Cloud Native Services**
  Use built-in cloud features so developers can focus on business logic rather than infrastructure.

---

### 🆚 Cloud Native vs Traditional Apps

| Feature                | Cloud Native                        | Traditional                   |
| ---------------------- | ----------------------------------- | ----------------------------- |
| **Predictability**     | High (easier debugging, monitoring) | Low (longer issue resolution) |
| **OS Abstraction**     | Yes (via containers)                | Tied to OS                    |
| **Capacity Planning**  | Right-sized                         | Often oversized               |
| **CI/CD Support**      | Built-in                            | Typically waterfall           |
| **Recovery & Scaling** | Fast (self-healing, auto-scaling)   | Manual, slower processes      |

---

## 🧱 Principles of Cloud Native Design

These are based on **The Twelve-Factor App** + 3 additional principles from *Beyond the Twelve-Factor App* by Kevin Hoffman.

> 📚 See: [12factor.net](https://12factor.net) and Kevin Hoffman's book.

---

### ✅ 15 Cloud Native Principles (Summarized)

1. **One Codebase per Application**

    * Each microservice has its own repo and Docker image.

2. **API First**

    * Design API before coding. Use Swagger/OpenAPI.

3. **Dependency Management**

    * Manage versions properly (e.g., with Maven).
    * Avoid version conflicts between dependencies.

4. **Design, Build, Release, Run**

    * **Design**: Choose tools, plan structure.
    * **Build**: Compile immutable artifact (e.g., `jar`).
    * **Release**: Add Dockerfiles, config, deploy setup.
    * **Run**: Start in the target environment.

5. **Separate Config & Code**

    * Externalize configurations, secrets (e.g., with Spring Cloud Config).

6. **Centralized Logs**

    * Aggregate logs (e.g., Loki, ELK stack).

7. **Disposability**

    * Fast startup and graceful shutdown (via containers).

8. **Backing Services**

    * Treat external services (e.g., SMTP, DB) as **attached resources**. Configurable via URLs.

9. **Environment Parity**

    * Keep development, staging, and production as similar as possible.
    * Follow **"You build it, you run it"** philosophy.

10. **Administrative Processes**

* Run via CLI or admin tools.
* Avoid running maintenance tasks during app startup.

❌ Bad example:

```java
public static void main(String[] args) {
    SpringApplication.run(MyApp.class, args);
    cleanupOldUsers(); // Deletes users every startup 😱
}
```

✅ Good practice:

```bash
java -jar your-app.jar --spring.profiles.active=cleanup
```

11. **Port Binding**

* App runs its own embedded server (e.g., Tomcat), binds to a port.

12. **Stateless Processes**

* Store state in external services (DBs, caches).
* App instances should be interchangeable.

13. **Concurrency**

* Use async queues & workers to offload long tasks.
* Example:

❌ Bad:

```java
userService.save(user);
emailService.send(); // slow
pdfService.generate(); // slow
return ok;
```

✅ Good:

```java
userService.save(user);
queue.push("send_email", user);
queue.push("generate_pdf", user);
return ok;
```

14. **Telemetry**

* Enable observability: metrics, traces, logs, dashboards.

15. **Authentication & Authorization**

* Secure all communications (HTTPS, OAuth, JWT, etc.).

---

### 🔧 Tools Commonly Used in Cloud Native Apps

* **Containers**: Docker
* **Orchestration**: Kubernetes
* **Build Tools**: Maven, Gradle
* **CI/CD**: Jenkins, GitHub Actions, GitLab CI
* **Config Management**: Spring Cloud Config
* **Logging**: Loki, ELK Stack
* **Monitoring**: Prometheus, Grafana
* **Messaging**: RabbitMQ, Kafka
* **Storage**: PostgreSQL, MongoDB, Redis

