package dev.ucomprotocol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a placed order in the commerce system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /** Unique identifier of the order. */
    private String id;

    /** ID of the order in the external commerce platform. */
    private String externalId;

    /** ID of the customer who placed the order. */
    private String customerId;

    /** Email address of the customer. */
    private String customerEmail;

    /** Currency code (ISO 4217) for the order. */
    private String currency;

    /** Current status of the order. */
    private OrderStatus status;

    /** Timestamp when the order was created. */
    private Instant createdAt;

    /** List of items included in the order. */
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    /** Total monetary amount of the order. */
    private BigDecimal totalAmount;

    /**
     * Represents a single line item within an order.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {
        /** Unique identifier of the order item. */
        private String id;

        /** ID of the product. */
        private String productId;

        /** Name of the product. */
        private String name;

        /** SKU of the product. */
        private String sku;

        /** Quantity ordered. */
        private int quantity;

        /** Price per unit at the time of order. */
        private BigDecimal unitPrice;

        /** Total price for this line item. */
        private BigDecimal totalPrice;
    }

    /**
     * Enumeration of possible order statuses.
     */
    public enum OrderStatus {
        PENDING,
        CONFIRMED,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        REFUNDED
    }
}
