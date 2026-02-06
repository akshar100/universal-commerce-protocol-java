package dev.ucomprotocol.discovery;

import dev.ucomprotocol.autoconfigure.UcpProperties;
import dev.ucomprotocol.spi.CommerceAdapter;
import dev.ucomprotocol.spi.CatalogAdapter;
import dev.ucomprotocol.spi.CartAdapter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UcpDiscoveryControllerSafeTest {

    @Test
    public void testCapabilitiesResolution() {
        // Mock Dependencies
        UcpProperties properties = new UcpProperties();
        properties.setProvider("shopify");

        CommerceAdapter mockAdapter = Mockito.mock(CommerceAdapter.class);

        // Only return Catalog and Cart adapters
        when(mockAdapter.getCatalogAdapter()).thenReturn(Mockito.mock(CatalogAdapter.class));
        when(mockAdapter.getCartAdapter()).thenReturn(Mockito.mock(CartAdapter.class));
        when(mockAdapter.getOrderAdapter()).thenReturn(null);
        when(mockAdapter.getCustomerAdapter()).thenReturn(null);

        // Initialize Controller
        UcpDiscoveryController controller = new UcpDiscoveryController(properties, mockAdapter);

        // Execute
        UcpDiscoveryController.DiscoveryResponse response = controller.getDiscoveryInfo();

        // Verify
        assertEquals("1.0", response.ucpVersion());
        assertEquals("shopify", response.provider());
        assertEquals(2, response.capabilities().size());
        assertTrue(response.capabilities().contains("catalog"));
        assertTrue(response.capabilities().contains("cart"));
    }
}
