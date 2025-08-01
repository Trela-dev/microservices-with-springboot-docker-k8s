---

# Section 20: Introduction to kubernetes Ingress, ServiceMesh (Istio) & mTLS

---

## In this section, we will learn a best practice that **every microservices developer** should follow.

We'll copy the code from **Section 14**, and we **won’t dive into Kubernetes** here. Instead, we’ll use the code from Section 14 as-is.

If we take a look at our `pom.xml` files in each microservice, we’ll notice something:

We have different configuration properties in each `pom.xml`, such as:

```xml
<properties>
  <java.version>21</java.version>
  <spring-cloud.version>2025.0.0</spring-cloud.version>
  <otelVersion>2.18.0</otelVersion>
</properties>
```

These are things like Java version, Spring Cloud version, OpenTelemetry version, etc.—and this is **repeated across all microservices**.

Then we have all the dependencies, and at the end, even the Google Jib Maven plugin.

### The Problem

The problem with this approach is that we're **hardcoding versions** like Spring, Java, and others **directly inside each microservice**.

Now imagine your organization has **more than 30 microservices**.

If you want to upgrade from one version to another, you would need to **manually update all the `pom.xml` files** in every microservice.

Do you think this is a good practice?
**Of course not!**

You would have to go into every single microservice just to change a single number.

---

### The Solution: Using a BOM

We'll solve this by using a **BOM**—a special `pom.xml` file that helps us **manage dependency versions centrally**.

---

### What is a BOM in Maven?

A **BOM (Bill of Materials)** is a dependency management mechanism that provides a **central place** to define the versions of dependencies (including transitive ones). It prevents version conflicts when different parts of your system use the same libraries but with **different versions**.

With a BOM, you can manage versions in **one place**, and downstream projects simply import the BOM to automatically align dependency versions.

In the context of **Maven and Spring Boot**, a BOM is a special kind of **POM (Project Object Model)** that manages versions of a **set of related dependencies**.

---

### Why Use a BOM in Microservices?

In a microservices architecture, managing shared dependencies can quickly become **complex**. Each service might declare versions independently, leading to version conflicts and high maintenance effort.

The BOM solves this by:

* ✅ Ensuring **version consistency** across all microservices
* ✅ **Simplifying dependency management**
* ✅ Making it easy to **upgrade libraries** across all services from one place

---

### What Defines a BOM in Maven?

A BOM is simply a Maven project (`pom.xml`) with:

```xml
<packaging>pom</packaging>
```

It typically doesn't have a `<dependencies>` section (or only minimal dependencies), but **does include a `<dependencyManagement>` block** that defines the versions of dependencies.

---

### Adapting a BOM to Our Project

To do this, we first create a **separate Maven project**—an empty Spring Boot project with a `pom.xml`.

We start with a basic empty Spring Boot project:

*(referenced image: img.png)*

It contains a typical `pom.xml` structure:

*(referenced image: img\_1.png)*

Before we make any changes, we **delete the entire `src` folder**, since this project won’t contain any source code.

*(referenced image: img\_2.png)*

---

### Clean Up the `pom.xml`

Next, we **remove the `<parent>` tag**:

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.5.4</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>
```

We also **remove any empty tags**.

Then, we add metadata like:

```xml
<licenses>
  <license>
    <name>Apache License, Version 2.0</name>
    <url>https://www.apache.org/licenses/LICENSE-2.0</url>
  </license>
</licenses>

<developers>
  <developer>
    <name>Marcin Trela</name>
    <email>marcin.trela.dev@gmail.com</email>
    <organization>Trela-dev</organization>
    <organizationUrl>https://github.com/Trela-dev</organizationUrl>
  </developer>
</developers>

<scm>
  <url>https://github.com/Trela-dev/microservices-with-springboot-docker-k8s</url>
</scm>
```

We also add:

```xml
<packaging>pom</packaging>
```

And remove:

```xml
<properties>
  <java.version>21</java.version>
</properties>
```

Now we **paste in the shared properties** that are used in all of our microservices:

```xml
<common-lib.version>1.0.0</common-lib.version>
<spring-boot.version>3.4.1</spring-boot.version>
<java.version>21</java.version>
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
<spring-cloud.version>2024.0.0</spring-cloud.version>
<spring-doc.version>2.8.1</spring-doc.version>
<h2.version>2.3.232</h2.version>
<lombok.version>1.18.36</lombok.version>
<otel.version>2.11.0</otel.version>
<micrometer.version>1.14.2</micrometer.version>
<jib.version>3.4.2</jib.version>
<image.tag>s20</image.tag>
```

---

### Controlling Third-Party Dependencies

When using **third-party dependencies** outside of Spring Boot (e.g., `H2`, `Lombok`, `OpenTelemetry`, `Micrometer`, etc.), you should **explicitly define the version** you want to use.

Even if you **don’t** specify the version, your microservices will **still work**, because Spring Boot starter dependencies will resolve the versions automatically.

However, you should **never rely on that**. You should always **control the versions** yourself to avoid surprises and ensure consistency.

---

### Add `<dependencyManagement>` Block

For now, let’s remove all regular dependencies and keep the `<dependencies>` section empty.

Then we add a new block:

```xml
<dependencyManagement>
  <dependencies>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>${spring-boot.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>

    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>${lombok.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>

    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <version>${h2.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>

    <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>${spring-doc.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <version>${spring-boot.version}</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-dependencies</artifactId>
      <version>${spring-cloud.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>

  </dependencies>
</dependencyManagement>
```

---

### Explanation

In the first dependency:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-dependencies</artifactId>
  <version>${spring-boot.version}</version>
  <type>pom</type>
  <scope>import</scope>
</dependency>
```

We are importing the Spring Boot BOM, which includes the recommended versions of all Spring Boot-related dependencies.

> This **doesn’t install any dependencies**, it just tells Maven:
>
> “Use the versions defined in this BOM whenever one of these dependencies is declared.”

---

### Example BOM File

You can find an example Spring Boot BOM here:
[https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-dependencies/3.2.0/spring-boot-dependencies-3.2.0.pom](https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-dependencies/3.2.0/spring-boot-dependencies-3.2.0.pom)

Inside, you’ll see:

* A list of `<properties>` defining dependency versions
* A `<dependencyManagement>` section listing all managed dependencies

This tells Maven:
**“If using Spring Boot version 3.2.0, use the dependency versions listed below.”**

---

## Adapting the `trela-bom` into Our Microservices

Now, to adapt our `trela-bom` into the project, we’ll go to the `pom.xml` file of one of our microservices — for example, `accounts`.

Up until now, our `pom.xml` has the following `parent`:

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.5.3</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>
```

But we want to **replace** this parent with our custom `trela-bom`:

```xml
<parent>
  <groupId>dev.trela</groupId>
  <artifactId>trela-bom</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <relativePath>../trela-bom/pom.xml</relativePath> <!-- lookup parent from repository -->
</parent>
```

---

### Removing Redundant Properties

We can also remove the `<properties>` section from `accounts` because all version properties are now managed by the `trela-bom`:

```xml
<properties>
  <java.version>21</java.version>
  <spring-cloud.version>2025.0.0</spring-cloud.version>
  <otelVersion>2.18.0</otelVersion>
</properties>
```

---

### The Benefit of the BOM

The beauty of this setup is that **if we ever want to update a version** (e.g., Spring Boot), we can just **change it once in `trela-bom`**, and **we don’t need to touch any microservice `pom.xml` files** — unless we want to add a new dependency.

---

### Keeping Only What’s Needed

When you look at the `accounts` microservice, you’ll notice that it only needs a **small subset of Spring Boot dependencies**.

There are hundreds of dependencies managed by the Spring Boot BOM, but `accounts` might only need 4 or 5.
The BOM won’t pull in everything by default — the microservice must still **declare explicitly which dependencies it needs**.

---

### Spring Initializr and BOM Behavior

When we generate a project using [Spring Initializr](https://start.spring.io), Spring Boot includes its own BOM (`spring-boot-dependencies`) by default.

👉 This BOM includes default, tested versions of popular libraries such as Jackson, Hibernate, Spring Web, Spring Security, etc.
👉 As a result, **you don’t need to manually specify versions** for most Spring Boot starters — the BOM handles it.

---

### Third-Party Dependencies — Pin Your Versions

However, for **third-party libraries** that are **outside of Spring Boot's ecosystem** — like OpenTelemetry or Micrometer — it’s **recommended to specify the exact version** you want to use.

Why?
Because if you don’t, **Spring Boot may pick the latest compatible version**, and that might change over time.

By specifying the version yourself (in your BOM), you **gain control and stability**.

---

### Example: Declaring a Third-Party Dependency in `accounts`

Let’s explicitly declare a third-party dependency in `accounts/pom.xml` like so:

```xml
<dependency>
  <groupId>io.micrometer</groupId>
  <artifactId>micrometer-registry-prometheus</artifactId>
  <version>${micrometer.version}</version>
</dependency>
```

This version is managed in `trela-bom`, so we don’t hardcode it here.

---

### Removing Redundant Dependency Management

Previously, we might have imported the Spring Cloud BOM like this:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-dependencies</artifactId>
      <version>${spring-cloud.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

But now, since this is already included in `trela-bom`, **we can safely remove it**.

---

### Cleaning Up and Finalizing the `pom.xml`

We replace all values from the local `<properties>` block with centralized ones from `trela-bom`.
Now our `pom.xml` has **no versions** — all are delegated to the BOM.

---

## Repeat for Other Services (e.g., `cards`)

We do the same in the `cards` microservice:

* Remove the old `<parent>` and replace it with the `trela-bom`

* Remove the `<properties>` block:

  ```xml
  <properties>
    <java.version>21</java.version>
    <spring-cloud.version>2025.0.0</spring-cloud.version>
    <otelVersion>2.18.0</otelVersion>
  </properties>
  ```

* Add necessary dependency versions via properties managed in `trela-bom`.

---

### Example: Adding Flyway

Let’s say you’re using **Flyway** (which your instructor isn't).
Since it’s a third-party dependency, you should pin its version in the `trela-bom`.

You can use the following command to find out the current version:

```bash
mvn dependency:tree
```

If it shows version `11.7.2`, you add that to the `trela-bom` like this:

```xml
<flyway.version>11.7.2</flyway.version>
```

Then in your microservice:

```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
  <version>${flyway.version}</version>
</dependency>
```

---

### Include `jib.version` and `image.tag` in BOM

You can also centralize the version of the **Jib plugin** and the image tag by adding to `trela-bom`:

```xml
<jib.version>3.4.2</jib.version>
<image.tag>s20</image.tag>
```

And do the same in the remaining microservices.

---

## Testing the BOM in Action

Let’s test the effect of the BOM.

We start the `config-server`, and we see that it runs with:

```text
:: Spring Boot :: (v3.4.1)
```

Now, we **change the Spring Boot version** in `trela-bom` to `3.4.0`.

Then, we **delete the `target` folder** from the `config-server` (so nothing is cached or precompiled).

*(image referenced: img\_3.png)*

After restarting, we see in the logs:

```text
:: Spring Boot :: (v3.4.0)
```

✅ That confirms our BOM is working perfectly!

---

## Managing Exceptions and Shared Dependencies with `trela-bom`

With the `trela-bom` project, we’ve built a **Bill of Materials (BOM)** for our microservices-based system.

However, there are cases where **a specific microservice needs to use a different version of a dependency** than the others.

For example, in our BOM we have the following version definitions:

```xml
<properties>
  <common-lib.version>1.0.0</common-lib.version>
  <spring-boot.version>3.4.1</spring-boot.version>
  <java.version>21</java.version>
  <maven.compiler.source>21</maven.compiler.source>
  <maven.compiler.target>21</maven.compiler.target>
  <spring-cloud.version>2024.0.0</spring-cloud.version>
  <spring-doc.version>2.8.1</spring-doc.version>
  <h2.version>2.3.232</h2.version>
  <lombok.version>1.18.36</lombok.version>
  <otel.version>2.18.0</otel.version>
  <micrometer.version>1.14.2</micrometer.version>
  <flyway.version>11.7.2</flyway.version>
  <jib.version>3.4.2</jib.version>
  <image.tag>s20</image.tag>
</properties>
```

Now let’s say we have the `loans` microservice, and we want it to use **a different version of `springdoc` and `lombok`** compared to the rest of the system.

To do this, we can **simply override the versions locally** in the `loans` project. Maven will use the version declared in the child project instead of the one inherited from the parent BOM.

---

## Another Benefit of the BOM: Shared Dependencies

Aside from centralizing version management, another great advantage of using a BOM is the ability to define **shared dependencies** in one place.

For example, let’s say **all microservices use `spring-boot-starter-test`**.

Instead of repeating this dependency in every microservice, we can move it to `trela-bom`.

---

### How to Do It

First, remove the `spring-boot-starter-test` dependency from each microservice:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
```

Once removed from all services, you can add it to the `dependencyManagement` section of `trela-bom`.

Now, every microservice will automatically inherit the version and scope from the BOM when they declare the dependency without a version.

---

## Verifying the Setup with Docker Images

As a next step, let’s test whether the `trela-bom` configuration affects the Docker image build process.

Navigate to the `config-server` folder and build the Docker image using Jib:

```bash
mvn compile jib:dockerBuild
```

✅ **Everything works!**
This confirms that our BOM setup didn’t break anything and that the microservices are building correctly with shared dependency and version management.

---

Czasem też developerzy piszą zduplikowany kod w wielu mikroserwisach
np mamy naprzyklad 
ErrorResponseDto
