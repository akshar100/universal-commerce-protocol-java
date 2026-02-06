package dev.ucomprotocol.spi;

/**
 * Main SPI interface for UCP adapters.
 * Implementations should provide access to specific capability adapters.
 */
public interface CommerceAdapter {
    /**
     * Returns the catalog adapter for product operations.
     */
    CatalogAdapter getCatalogAdapter();

    /**
     * Returns the cart adapter for shopping cart operations.
     */
    CartAdapter getCartAdapter();

    /**
     * Returns the order adapter for order management operations.
     */
    OrderAdapter getOrderAdapter();

    /**
     * Returns the customer adapter for customer management operations.
     */
    CustomerAdapter getCustomerAdapter();
}
