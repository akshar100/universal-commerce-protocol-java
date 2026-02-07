package dev.ucomprotocol.spi;

import dev.ucomprotocol.model.Cart;

/**
 * Adapter interface for shopping cart operations.
 * <p>
 * Implement this interface to connect to a commerce platform's cart
 * functionality.
 */
public interface CartAdapter {
    /**
     * Retrieves a cart by its ID.
     *
     * @param cartId the unique identifier of the cart
     * @return the requested cart, or null if not found
     */
    Cart getCart(String cartId);

    /**
     * Creates a new cart for a customer.
     *
     * @param customerId the unique identifier of the customer (optional)
     * @return the newly created cart
     */
    Cart createCart(String customerId);

    /**
     * Adds an item to the cart.
     *
     * @param cartId    the unique identifier of the cart
     * @param productId the unique identifier of the product to add
     * @param quantity  the quantity of the product to add
     * @return the updated cart
     */
    Cart addToCart(String cartId, String productId, int quantity);

    /**
     * Removes an item from the cart.
     *
     * @param cartId the unique identifier of the cart
     * @param itemId the unique identifier of the item to remove
     * @return the updated cart
     */
    Cart removeFromCart(String cartId, String itemId);

    /**
     * Updates the quantity of an item in the cart.
     *
     * @param cartId   the unique identifier of the cart
     * @param itemId   the unique identifier of the item to update
     * @param quantity the new quantity
     * @return the updated cart
     */
    Cart updateItemQuantity(String cartId, String itemId, int quantity);
}
