package dev.ucomprotocol.discovery;

import dev.ucomprotocol.autoconfigure.UcpProperties;
import dev.ucomprotocol.spi.CommerceAdapter;
import dev.ucomprotocol.spi.CatalogAdapter;
import dev.ucomprotocol.spi.CustomerAdapter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

        // Only return Catalog and Customer (Identity) adapters
        when(mockAdapter.getCatalogAdapter()).thenReturn(Mockito.mock(CatalogAdapter.class));
        when(mockAdapter.getCustomerAdapter()).thenReturn(Mockito.mock(CustomerAdapter.class));
        when(mockAdapter.getCartAdapter()).thenReturn(null);
        when(mockAdapter.getOrderAdapter()).thenReturn(null);

        // Initialize Controller
        UcpDiscoveryController controller = new UcpDiscoveryController(properties, mockAdapter);

        // Execute
        UcpDiscoveryController.DiscoveryResponse response = controller.getDiscoveryInfo();

        // Verify
        assertEquals("2026-01-23", response.ucp().version());
        assertEquals(2, response.ucp().capabilities().size());
        assertTrue(response.ucp().capabilities().stream().anyMatch(c -> c.type().equals("dev.ucp.shopping.catalog")));
        assertTrue(response.ucp().capabilities().stream().anyMatch(c -> c.type().equals("dev.ucp.common.identity")));
    }
}
