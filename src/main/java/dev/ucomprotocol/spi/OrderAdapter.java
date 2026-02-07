package dev.ucomprotocol.spi;

import dev.ucomprotocol.model.Order;
import java.util.List;

/**
 * Adapter interface for order management operations.
 * <p>
 * Implement this interface to connect to a commerce platform's order processing
 * system.
 */
public interface OrderAdapter {
    /**
     * Creates an order from a cart.
     *
     * @param cartId the unique identifier of the cart to convert into an order
     * @return the created order
     */
    Order createOrder(String cartId);

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the unique identifier of the order
     * @return the requested order, or null if not found
     */
    Order getOrder(String orderId);

    /**
     * Retrieves all orders for a specific customer.
     *
     * @param customerId the unique identifier of the customer
     * @return a list of the customer's orders, or an empty list if none found
     */
    List<Order> getOrdersByCustomer(String customerId);

    /**
     * Cancels an existing order.
     *
     * @param orderId the unique identifier of the order to cancel
     * @return the updated order with cancelled status
     */
    Order cancelOrder(String orderId);
}
