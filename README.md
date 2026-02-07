# UCP Java - Spring Boot Starter

The **Universal Commerce Protocol (UCP)** library is a standardized, protocol-agnostic framework designed for Spring Boot. It abstracts the complexities of integrating with multiple commerce platforms (Shopify, Magento, Commercetools, etc.) into a single, unified interface.

## Features

* **Unified Domain Models:** Standardized schemas for `Product`, `Order`, `Cart`, and `Customer`.
* **Provider Agnostic:** Switch commerce providers via simple configuration changes without touching business logic.
* **Spring Boot Auto-Configuration:** Automatically wires up clients and adapters based on your properties.
* **Discovery Endpoint:** Automatically exposes protocol metadata at `/.well-known/ucp`.
* **Resilience:** Built-in support for circuit breaking using Resilience4j.

---

## Getting Started

### Prerequisites
* **Java 17+**
* **Spring Boot 3.x**

### Installation

Add the dependency to your `build.gradle`:

```groovy
dependencies {
    implementation 'dev.ucomprotocol:ucp-spring-boot-starter:1.0.0'
}
```

## Discovery Endpoint Configuration

One of the key features of UCP Java is the **Automatic Discovery Endpoint**. This allows clients to query your service and discover which capabilities it supports (e.g., does it support Carts? Orders?).

### How to Enable

To enable the `/.well-known/ucp` endpoint, you simply need to include the Spring Boot Web starter alongside UCP Java. The library detects if you are running a web application and automatically registers the controller.

**build.gradle:**
```groovy
dependencies {
    // 1. Add UCP Java
    implementation 'dev.ucomprotocol:ucp-spring-boot-starter:1.0.0'
    
    // 2. Ensure you have the Web Starter (Discovery only works in web apps)
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

### Verification

Once your application starts, you can verify the endpoint is active using `curl`:

```bash
curl http://localhost:8080/.well-known/ucp
```

**Response Example:**
```json
{
  "ucp": {
    "version": "2026-01-23",
    "capabilities": {
      "dev.ucp.shopping.catalog": [
         { "version": "2026-01-23", "spec": "..." }
      ],
      "dev.ucp.shopping.cart": [ ... ],
      "dev.ucp.shopping.order": [ ... ],
      "dev.ucp.common.identity": [ ... ]
    }
  }
}
```

This response tells you:
1.  **Protocol Version**: 2026-01-23
2.  **Capabilities**: A map of supported capabilities and their details.

### Changing Providers

To return a different provider in the discovery info, simply change your configuration in `application.properties`:

```properties
ucp.provider=shopify
```

The endpoint will use the Shopify adapter to determine capabilities.
