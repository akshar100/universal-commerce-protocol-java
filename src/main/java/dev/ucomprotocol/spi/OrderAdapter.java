package dev.ucomprotocol.spi;

import dev.ucomprotocol.model.Order;
import java.util.List;

public interface OrderAdapter {
    Order createOrder(String cartId);

    Order getOrder(String orderId);

    List<Order> getOrdersByCustomer(String customerId);

    Order cancelOrder(String orderId);
}
