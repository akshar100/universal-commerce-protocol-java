package dev.ucomprotocol.spi.mock;

import dev.ucomprotocol.model.Cart;
import dev.ucomprotocol.model.Customer;
import dev.ucomprotocol.model.Order;
import dev.ucomprotocol.model.Product;
import dev.ucomprotocol.spi.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MockCommerceAdapter implements CommerceAdapter {

    @Override
    public CatalogAdapter getCatalogAdapter() {
        return new CatalogAdapter() {
            @Override
            public Optional<Product> getProductById(String id) {
                return Optional.of(Product.builder()
                        .id(id)
                        .name("Mock Product")
                        .price(BigDecimal.TEN)
                        .currency("USD")
                        .build());
            }

            @Override
            public List<Product> searchProducts(String query) {
                return Collections.emptyList();
            }

            @Override
            public List<Product> getProductsByCategory(String categoryId) {
                return Collections.emptyList();
            }
        };
    }

    @Override
    public CartAdapter getCartAdapter() {
        return new CartAdapter() {
            @Override
            public Cart getCart(String cartId) {
                return Cart.builder().id(cartId).build();
            }

            @Override
            public Cart createCart(String customerId) {
                return Cart.builder().id("mock-cart-" + System.currentTimeMillis()).customerId(customerId).build();
            }

            @Override
            public Cart addToCart(String cartId, String productId, int quantity) {
                return Cart.builder().id(cartId).build();
            }

            @Override
            public Cart removeFromCart(String cartId, String itemId) {
                return Cart.builder().id(cartId).build();
            }

            @Override
            public Cart updateItemQuantity(String cartId, String itemId, int quantity) {
                return Cart.builder().id(cartId).build();
            }
        };
    }

    @Override
    public OrderAdapter getOrderAdapter() {
        return new OrderAdapter() {
            @Override
            public Order createOrder(String cartId) {
                return Order.builder().id("mock-order").build();
            }

            @Override
            public Order getOrder(String orderId) {
                return Order.builder().id(orderId).build();
            }

            @Override
            public List<Order> getOrdersByCustomer(String customerId) {
                return Collections.emptyList();
            }

            @Override
            public Order cancelOrder(String orderId) {
                return Order.builder().id(orderId).status(Order.OrderStatus.CANCELLED).build();
            }
        };
    }

    @Override
    public CustomerAdapter getCustomerAdapter() {
        return new CustomerAdapter() {
            @Override
            public Optional<Customer> getCustomer(String id) {
                return Optional.of(Customer.builder().id(id).firstName("Mock").lastName("User").build());
            }

            @Override
            public Customer createCustomer(Customer customer) {
                return customer;
            }

            @Override
            public Customer updateCustomer(String id, Customer customer) {
                return customer;
            }
        };
    }
}
