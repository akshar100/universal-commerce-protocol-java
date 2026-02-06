package dev.ucomprotocol.spi.mock;

import dev.ucomprotocol.model.Cart;
import dev.ucomprotocol.model.Customer;
import dev.ucomprotocol.model.Order;
import dev.ucomprotocol.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MockCommerceAdapterTest {

    private MockCommerceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new MockCommerceAdapter();
    }

    @Test
    void catalogAdapterShouldReturnMockProduct() {
        Optional<Product> product = adapter.getCatalogAdapter().getProductById("123");
        assertThat(product).isPresent();
        assertThat(product.get().getId()).isEqualTo("123");
        assertThat(product.get().getName()).isEqualTo("Mock Product");
    }

    @Test
    void cartAdapterShouldCreateCart() {
        Cart cart = adapter.getCartAdapter().createCart("cust-1");
        assertThat(cart).isNotNull();
        assertThat(cart.getCustomerId()).isEqualTo("cust-1");
        assertThat(cart.getId()).startsWith("mock-cart-");
    }

    @Test
    void orderAdapterShouldReturnCancelledOrder() {
        Order order = adapter.getOrderAdapter().cancelOrder("ord-1");
        assertThat(order.getId()).isEqualTo("ord-1");
        assertThat(order.getStatus()).isEqualTo(Order.OrderStatus.CANCELLED);
    }

    @Test
    void customerAdapterShouldReturnMockCustomer() {
        Optional<Customer> customer = adapter.getCustomerAdapter().getCustomer("cust-1");
        assertThat(customer).isPresent();
        assertThat(customer.get().getFirstName()).isEqualTo("Mock");
    }
}
