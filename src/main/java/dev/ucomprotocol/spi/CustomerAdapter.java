package dev.ucomprotocol.spi;

import dev.ucomprotocol.model.Customer;
import java.util.Optional;

public interface CustomerAdapter {
    Optional<Customer> getCustomer(String id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(String id, Customer customer);
}
