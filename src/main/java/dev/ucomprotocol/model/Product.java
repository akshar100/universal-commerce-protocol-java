package dev.ucomprotocol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private String currency; // ISO 4217 code
    private Map<String, Object> attributes;
    private String imageUrl;
}
