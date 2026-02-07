package dev.ucomprotocol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Represents a product in the commerce catalog.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /** Unique identifier of the product. */
    private String id;

    /** Stock Keeping Unit code. */
    private String sku;

    /** Name of the product. */
    private String name;

    /** Detailed description of the product. */
    private String description;

    /** Price of the product. */
    private BigDecimal price;

    /** Currency code (ISO 4217) for the price. */
    private String currency;

    /** Additional dynamic attributes for the product. */
    private Map<String, Object> attributes;

    /** URL to an image of the product. */
    private String imageUrl;
}
