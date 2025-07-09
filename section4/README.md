
## 🧱 **Section 4: Practical – Working with Docker**

> *(There is no Section 3 because it was purely theoretical. Section 4 is the next practical segment.)*

---

### 🚢 **What is Docker?**

* Docker allows us to **run each application in an isolated containerized environment**, ensuring that:

    * Applications work **consistently** across all systems.
    * Each app runs in its **own ecosystem** (container), avoiding conflicts.
* 🖥️ **Docker vs Virtual Machines (VMs):**

    * Docker containers are **lighter** and **start much faster** than VMs.
    * Docker containers do **not have compatibility issues**, unlike VMs.
    * No more "it works on my machine" problems! 🙅‍♂️

---

## 🔧 **Three Approaches to Building Docker Images**

---

### 🛠️ **1. Dockerfile Approach (Manual Method)**

This is the **most complex** but gives you **full control**.

A `Dockerfile` is a script with instructions on **how to build an image**.

#### 🔑 Common Commands:

```bash
# 🔨 Build an image
docker build . -t treladev/accounts:s4

# 🚀 Run container from image
docker run -p 8080:8080 treladev/accounts:s4

# 👀 View running containers
docker ps

# 📦 View all containers (running + stopped)
docker ps -a

# 🗂️ Start a stopped container
docker start <container_id>

# 🛑 Stop a container
docker stop <container_id>

# 🌀 Run multiple containers on different ports
docker run -p 8081:8080 treladev/accounts:s4
```

You can also manage everything visually using **Docker Desktop**.

---

### 🎒 **2. Buildpacks Approach (Simplified Method)**

* Easier than Dockerfile.
* Uses **pre-built intelligent image logic** based on years of best practices.
* ❗️**Downside**: You don’t have full control over what happens "under the hood."

#### Steps:

1. Add the image name in your Spring Boot `pom.xml` (e.g. in `loans` project):

```xml
<image>
  <name>treladev/${project.artifactId}:s4</name>
</image>
```

2. Run the command:

```bash
mvn spring-boot:build-image
```

This will build the image using **Buildpacks**.

3. To run:

```bash
docker run -d -p 8080:8090 treladev/loans:s4
```

You’ll also notice an additional base image used by Buildpacks, e.g.:

```
paketobuildpacks/builder-noble-java-tiny
```

---

### ☁️ **3. Jib by Google (Java Only)**

Official site: [https://github.com/GoogleContainerTools/jib](https://github.com/GoogleContainerTools/jib)

* Jib allows building Docker images **without Docker installed locally**.
* ✅ Faster than Buildpacks.
* ❌ Only works with Java applications.

#### How to use:

1. Add the plugin to `pom.xml` (e.g. in `cards` project):

```xml
<plugin>
  <groupId>com.google.cloud.tools</groupId>
  <artifactId>jib-maven-plugin</artifactId>
  <version>3.4.6</version>
  <configuration>
    <to>
      <image>treladev/${project.artifactId}:s4</image>
    </to>
  </configuration>
</plugin>
```

2. Build the image:

```bash
mvn compile jib:dockerBuild
```

3. Run the image (as usual):

```bash
docker run -d -p 9000:9000 treladev/cards:s4
```

#### 🚀 **Jib without Docker installed:**

* Builds OCI (Open Container Initiative) images.
* Can push directly to:

    * Docker Hub
    * GitHub Container Registry
    * Google Container Registry
* **No Docker daemon required**!

---

### 📤 **Pushing Images to Docker Hub**

> There are alternatives to Docker Hub, but in this course we use Docker Hub.

To push your image:

```bash
docker image push docker.io/treladev/accounts:s4
```

✅ If you're already logged in, it won’t ask for credentials.

❗️*Note: In this case, the pushed image wasn’t visible on Docker Hub due to a bug, but pulling still worked.*

To pull:

```bash
docker pull treladev/loans:s4
```

---

### 🧩 **Using Docker Compose**

If you have multiple containers or services, use `docker-compose` to manage them easily.

#### Commands:

```bash
# ⬆️ Start all services defined in docker-compose.yml
docker compose up -d

# ⬇️ Stop all running containers
docker compose down
```

---

### 🧠 **Summary: Which approach is best?**

* **Buildpacks** – simple, recommended for most use cases.
* **Dockerfile** – advanced, fully customizable.
* **Jib** – fast, works without Docker, but only for Java.

👨‍🏫 The instructor prefers Jib during the course due to **macOS issues** with Buildpacks and **faster image builds**.


