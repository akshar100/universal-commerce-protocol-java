package dev.ucomprotocol.spi;

import dev.ucomprotocol.model.Cart;

public interface CartAdapter {
    Cart getCart(String cartId);

    Cart createCart(String customerId);

    Cart addToCart(String cartId, String productId, int quantity);

    Cart removeFromCart(String cartId, String itemId);

    Cart updateItemQuantity(String cartId, String itemId, int quantity);
}
