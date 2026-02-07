package dev.ucomprotocol.spi;

import dev.ucomprotocol.model.Product;
import java.util.List;
import java.util.Optional;

/**
 * Adapter interface for product catalog operations.
 * <p>
 * Implement this interface to connect to a commerce platform's product catalog.
 */
public interface CatalogAdapter {
    /**
     * Retrieves a product by its ID.
     *
     * @param id the unique identifier of the product
     * @return an {@link Optional} containing the product if found, or empty
     *         otherwise
     */
    Optional<Product> getProductById(String id);

    /**
     * Searches for products matching a query string.
     *
     * @param query the search query
     * @return a list of matching products, or an empty list if none found
     */
    List<Product> searchProducts(String query);

    /**
     * Retrieves products belonging to a specific category.
     *
     * @param categoryId the unique identifier of the category
     * @return a list of products in the category, or an empty list if none found
     */
    List<Product> getProductsByCategory(String categoryId);
}
