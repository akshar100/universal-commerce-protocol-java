package dev.ucomprotocol.spi;

import dev.ucomprotocol.model.Customer;
import java.util.Optional;

/**
 * Adapter interface for customer management operations.
 * <p>
 * Implement this interface to connect to a commerce platform's customer data.
 */
public interface CustomerAdapter {
    /**
     * Retrieves a customer by their ID.
     *
     * @param id the unique identifier of the customer
     * @return an {@link Optional} containing the customer if found, or empty
     *         otherwise
     */
    Optional<Customer> getCustomer(String id);

    /**
     * Creates a new customer.
     *
     * @param customer the customer data to create
     * @return the created customer
     */
    Customer createCustomer(Customer customer);

    /**
     * Updates an existing customer.
     *
     * @param id       the unique identifier of the customer to update
     * @param customer the updated customer data
     * @return the updated customer
     */
    Customer updateCustomer(String id, Customer customer);
}
