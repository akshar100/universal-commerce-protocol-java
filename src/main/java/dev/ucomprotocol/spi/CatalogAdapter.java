package dev.ucomprotocol.spi;

import dev.ucomprotocol.model.Product;
import java.util.List;
import java.util.Optional;

public interface CatalogAdapter {
    Optional<Product> getProductById(String id);

    List<Product> searchProducts(String query);

    List<Product> getProductsByCategory(String categoryId);
}
