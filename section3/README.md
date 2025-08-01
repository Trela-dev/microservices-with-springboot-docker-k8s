---

# Section 3:  Introduction to Microservices Architecture

---

**How to properly define the size and boundaries of microservices?**

🔹 **The Challenge**
One of the most difficult aspects of building microservices is properly defining their boundaries and appropriately “sizing” each one.

🔸 **Industry Approaches**

1. **Domain-Driven Sizing (DDD)**

* Based on the business domain and its needs.
* Microservice boundaries align with business contexts (e.g., accounts, cards, loans).
* Requires good domain knowledge and time.
* Microservices are aligned with so-called *bounded contexts*.

2. **Event Storming Sizing**

* An interactive session involving stakeholders (business, IT, architects).
* Identifies key system events, such as “Payment Completed,” “Product Search.”
* Based on these events, commands and reactions are defined, then grouped into services.
* Result: service boundaries emerge naturally from the event flow.

🔸 **Example from the Banking Sector**

❌ **Incorrect Division**:

* Combining independent modules into a single service (e.g., Cards & Loans together).
* Too many functionalities in one service – lack of cohesion, hard to scale.

✅ **Correct Division**:

* Separate microservices for: Saving Account, Trading Account, Credit/Debit Cards, Loans (Home, Vehicle, Personal).
* Modules are loosely coupled and strongly cohesive internally.

🔸 **Case Study: Monolith to Microservices Migration (E-commerce)**

✅ **Drawbacks of Monolithic Architecture**:

* Initially easy to build and deploy, but:

    * The application quickly becomes too complex.
    * Hard to implement changes – one bug can bring down the whole system.
    * Small changes require full redeployment.
    * Lack of technological flexibility.
    * Challenges with small teams and Agile approach.

✅ **New Microservices Architecture**:

* Each microservice handles a single functionality (e.g., Identity, Orders, Catalog, Invoices, Sales, Marketing).
* Services are deployed as Docker containers, communicating via REST/async.
* Data stored in dedicated databases (MongoDB, RDBMS, Redis, etc.).
* The frontend (Angular/React) communicates through an API Gateway.

🔸 **Strangler Fig Pattern – Migration Strategy**

**What is it?**

* A pattern that enables gradual migration from a monolithic system to microservices.
* Named after the *strangler fig* tree – grows around an existing tree until it fully replaces it.

**When to use it?**

* For large, complex systems where:

    * A “big bang” migration is not feasible.
    * The system must remain operational during the migration.

**Migration Stages**:

1. **Identification** – selecting functionalities for refactoring.
2. **Transformation** – rewriting selected functionality as a microservice.
3. **Coexistence** – both systems (monolith and microservices) operate simultaneously.
4. **Elimination** – removing the old logic from the monolith.

**Supporting Tools**:

* **API Gateway** – to manage traffic between the monolith and microservices.
* **Docker, Kubernetes** – for containerization and orchestration.
