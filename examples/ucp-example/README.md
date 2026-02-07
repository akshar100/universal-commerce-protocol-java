# UCP Example Application

This is an example Spring Boot application demonstrating how to use the `universal-commerce-protocol-java` library.

## Project Structure

This project is configured as a composite build, including the root `universal-commerce-protocol-java` build. This allows it to use the local version of the library without needing to publish artifacts to a repository.

## Prerequisites

- Java 17 or higher
- Git

## How to Run

1. Navigate to the example directory:
   ```bash
   cd examples/ucp-example
   ```

2. Run the application using Gradle:
   ```bash
   ../../gradlew bootRun
   ```

   The application will start on port **8081**.

## Verify the Application

Once the application is running, you can access the UCP Discovery endpoint:

```bash
curl http://localhost:8081/.well-known/ucp
```

Expected output (JSON):
```json
{
  "ucp": {
    "version": "2026-01-23",
    "capabilities": [
      {
        "type": "dev.ucp.shopping.catalog",
        "version": "2026-01-23",
        "spec": "https://ucp.dev/specification/catalog"
      },
      {
        "type": "dev.ucp.shopping.cart",
        "version": "2026-01-23",
        "spec": "https://ucp.dev/specification/cart"
      },
      {
        "type": "dev.ucp.shopping.order",
        "version": "2026-01-23",
        "spec": "https://ucp.dev/specification/order"
      },
      {
        "type": "dev.ucp.common.identity",
        "version": "2026-01-23",
        "spec": "https://ucp.dev/specification/identity"
      }
    ],
    "services": []
  }
}
```

The capabilities listed depend on what the Mock Adapter is configured to support (by default, it supports all core capabilities).

## Configuration

The application is configured in `src/main/resources/application.properties`:

```properties
server.port=8081
ucp.provider=mock
```

You can change `ucp.provider` to switch implementations (e.g., `shopify` if available).
