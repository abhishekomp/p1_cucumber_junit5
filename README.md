# User Service Project

A simple Java service for user registration and notifications.

## Features

- Register new users
- Send notifications to users

## Technologies

- Java
- Maven

## Project Structure

- `src/main/java/org/example/services/` \- Service classes
- `src/test/java/org/example/services/` \- Test classes

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Build

```sh
mvn clean install

mvn test
---

# Spring Context Configuration in Cucumber Java Projects

When using **Spring dependency injection** in a Cucumber BDD project, there are two main ways you might see Spring’s `@ContextConfiguration` annotation used:

- **Centrally, on a configuration class (`CucumberSpringConfig`)** — **RECOMMENDED**
- **Directly on each Step Definition class** — *Discouraged in most BDD automation*

This guide explains **the difference, when/why to use each, and the resulting project structure**.

---

## Why Does It Matter?

- Correct config choice ensures **scenario-scope, DI, and isolation work as intended** across all step definition classes.
- Centralizing Spring config **reduces bugs, speeds up test startup, and supports data sharing via scenario-scoped beans**.

---

## 1️⃣ Recommended: Central `@ContextConfiguration` (`CucumberSpringConfig`)

Create a single Spring config in your glue package:

```java
package org.example.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

// Centralizes Spring wiring for all stepdefs and hooks
@CucumberContextConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class CucumberSpringConfig {}
```

**You can define beans used in stepdefs in `TestConfig`:**

```java
package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public SomeService someService() {
        return new SomeServiceImpl();
    }
    // Add @ScenarioScope beans here as needed!
}
```

#### **All your StepDef classes need NO extra annotations:**

```java
package org.example.steps;

public class UserRegistrationStepDefs {
    // @Autowired beans from TestConfig will work here
}
```

### **How does it work?**
- Cucumber Spring creates **one ApplicationContext for the test run** (not per scenario).
- `@ScenarioScope` beans are created ONCE per scenario and shared across all stepdefs/hooks **within that scenario**.
- All stepdefs in the glue config automatically share a context, making state/data sharing (via DI) reliable and isolated.

---

## 2️⃣ Per StepDef Class: `@ContextConfiguration` on Every Class

```java
@ContextConfiguration(classes = org.example.config.TestConfig.class)
public class UserRegistrationStepDefs {
    // ...
}
@ContextConfiguration(classes = org.example.config.TestConfig.class)
public class UserLoginStepDefs {
    // ...
}
```
**This pattern creates a potentially SEPARATE Spring ApplicationContext per stepdef class!**

- **Not recommended** for most test automation!
- Can cause:
    - Multiple context creation (slow, wasteful)
    - Scenario-scope beans **not shared** across classes
    - Broken context and data sharing between stepdefs

### Only use this for edge cases (special integration, non-BDD system tests).

---

## Best Practice Table

| Where is @ContextConfiguration? | What happens?                          | Use case                        |
|--------------------------|-------------------------------------------|---------------------------------|
| On central config class  | One context, shared by all steps/glue     | **Best practice** for BDD tests |
| On each stepdef class    | Multiple contexts, not shared             | Advanced/special cases only     |

---

## Example Project Structure

```text
src/test/java/
  org/example/
    config/
      CucumberSpringConfig.java  <-- annotate here!
      TestConfig.java
    steps/
      UserSteps.java
      OrderSteps.java
      CartSteps.java
    context/
      SharedContext.java
src/test/resources/
  features/
    your-feature.feature
```

---

## What Happens To StepDef Code?

- **No need for `@ContextConfiguration` on individual stepdef classes.**
- Just use `@Autowired` to inject context/services/beans:

```java
public class UserSteps {
    @Autowired
    private SharedContext data;
    // Use `data` in any scenario step
}
```

---

## When to Use @ScenarioScope?

Add `@ScenarioScope` to beans you want to be **created fresh** for each scenario, and automatically cleaned up afterwards:

```java
@Component
@ScenarioScope
public class SharedContext {
    // User, session, cart, or any data to share in steps of a scenario
}
```

---

## Summary: The Gold Standard

- ⬆️ *Centralize your Spring config with `@CucumberContextConfiguration` and `@ContextConfiguration` in one class in your glue code.*
- ⬆️ *Use `@ScenarioScope` for scenario/context beans you want shared only per scenario.*
- ⬆️ *Do not annotate every stepdef with `@ContextConfiguration` – this can break data sharing and performance!*
- ⬆️ *DI and scenario isolation “just work” for all your BDD glue code.*

---
