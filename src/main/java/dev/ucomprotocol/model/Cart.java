package dev.ucomprotocol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart in the commerce system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    /** Unique identifier of the cart. */
    private String id;

    /** ID of the customer who owns the cart. */
    private String customerId;

    /** Currency code (ISO 4217) for the cart prices. */
    private String currency;

    /** List of items currently in the cart. */
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();

    /** Total monetary amount of all items in the cart. */
    private BigDecimal totalAmount;

    /**
     * Represents a single item within a shopping cart.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItem {
        /** Unique identifier of the cart item. */
        private String id;

        /** ID of the product associated with this item. */
        private String productId;

        /** Name of the product. */
        private String name;

        /** SKU of the product. */
        private String sku;

        /** Quantity of the product in the cart. */
        private int quantity;

        /** Price per unit of the product. */
        private BigDecimal unitPrice;

        /** Total price for this line item (unitPrice * quantity). */
        private BigDecimal totalPrice;

        /** URL to an image of the product. */
        private String imageUrl;
    }
}
