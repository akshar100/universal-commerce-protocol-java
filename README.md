# Universal Commerce Protocol (UCP) - Spring Boot Starter

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue)

The **Universal Commerce Protocol (UCP)** library is a standardized, protocol-agnostic framework designed for Spring Boot. It abstracts the complexities of integrating with multiple commerce platforms (Shopify, Magento, Commercetools, etc.) into a single, unified interface.

---

## ## Features

* **Unified Domain Models:** Standardized schemas for `Product`, `Order`, `Cart`, and `Customer` objects.
* **Provider Agnostic:** Switch commerce providers via simple configuration changes without touching business logic.
* **Spring Boot Auto-Configuration:** Automatically wires up the necessary clients and adapters based on your properties.
* **Extensible SPI:** Easily add custom adapters for proprietary or niche commerce engines.
* **Resilience & Performance:** Built-in support for caching and circuit breaking (via Resilience4j).

---

## ## Getting Started

### ### Prerequisites
* **Java 17+**
* **Spring Boot 3.x**
* **Maven** or **Gradle**

### ### Installation
Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.yourdomain.ucp</groupId>
    <artifactId>ucp-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
