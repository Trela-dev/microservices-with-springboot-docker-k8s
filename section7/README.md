---

# Section 7:  Using MYSQL Database inside microservices

## Overview

Setting up and running MySQL database containers for microservices using Docker.

---

## 🐳 Docker Compose & MySQL Microservices Setup – Key Notes

### ✅ Switching from H2 to MySQL

* In Section 7, we switch the database from **H2** to **MySQL** for our microservices.
* To run a MySQL container manually (outside of `docker-compose`), use:

  ```bash
  docker run -p 3306:3306 --name accountsdb \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=accountsdb \
    -d mysql:8.0.36
  ```

### ✅ Updating `application.yml`

* Update the Spring Boot configuration to point to MySQL instead of H2:

  ```yaml
  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/accountsdb
      username: root
      password: root
    jpa:
      ...
  ```

### ✅ Running with `docker-compose`

* Add your MySQL container to `docker-compose.yml`:

  ```yaml
  accountsdb:
    image: mysql
    container_name: accountsdb
    ports:
      - 3306:3306
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: accountsdb
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      interval: 10s
      timeout: 5s
      retries: 5
  ```

* Health checks are useful to make sure the DB is ready before the application starts.

* You can find other valid healthcheck commands online depending on the database type.

### ✅ Internal vs External Ports

* In this example:

  ```yaml
  ports:
    - 3308:3306
  ```

  `3308` is the **host** port (external), and `3306` is the **container** port (internal).

* When configuring `SPRING_DATASOURCE_URL`, use the **internal container port**, and reference the **container name** as the host:

  ```env
  SPRING_DATASOURCE_URL=jdbc:mysql://cardsdb:3306/cardsdb
  ```

### ⚠️ Important: Container Networking

> **Containers must be in the same Docker network to communicate with each other by name.**

* This is crucial! In `docker-compose.yml`, make sure all services share the same network:

  ```yaml
  networks:
    - trelabank
  ```

* At the bottom of the file, define the network:

  ```yaml
  networks:
    trelabank:
      driver: bridge
  ```

* If containers are not in the same network, they **cannot resolve each other's names**, and connections will fail.

### 💾 Persistent Data

* If you delete a container, you lose its data **unless** you use volumes.

* In production, always mount volumes to persist data:

  ```yaml
  volumes:
    - mysql-data:/var/lib/mysql
  ```

* Define the volume at the bottom of the file:

  ```yaml
  volumes:
    mysql-data:
  ```

---
