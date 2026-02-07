package dev.ucomprotocol.discovery;

import dev.ucomprotocol.autoconfigure.UcpProperties;
import dev.ucomprotocol.spi.CommerceAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller that exposes the Universal Commerce Protocol (UCP) discovery
 * endpoint.
 * <p>
 * This endpoint is available at {@code /.well-known/ucp} and returns metadata
 * about the supported
 * capabilities of this commerce service. It adheres to the UCP specification,
 * using the canonical
 * namespace {@code dev.ucp} for all capabilities.
 * <p>
 * Capabilities are dynamically discovered based on the registered
 * {@link CommerceAdapter}.
 * - {@code dev.ucp.shopping.catalog}: Present if
 * {@link CommerceAdapter#getCatalogAdapter()} is not null.
 * - {@code dev.ucp.shopping.cart}: Present if
 * {@link CommerceAdapter#getCartAdapter()} is not null.
 * - {@code dev.ucp.shopping.order}: Present if
 * {@link CommerceAdapter#getOrderAdapter()} is not null.
 * - {@code dev.ucp.common.identity}: Present if
 * {@link CommerceAdapter#getCustomerAdapter()} is not null.
 */
@RestController
@RequestMapping("/.well-known/ucp")
public class UcpDiscoveryController {

    private final UcpProperties ucpProperties;
    private final CommerceAdapter commerceAdapter;

    public UcpDiscoveryController(UcpProperties ucpProperties, CommerceAdapter commerceAdapter) {
        this.ucpProperties = ucpProperties;
        this.commerceAdapter = commerceAdapter;
    }

    @GetMapping
    public DiscoveryResponse getDiscoveryInfo() {
        Map<String, List<CapabilityDetail>> capabilities = new HashMap<>();
        String ucpVersion = "2026-01-23";

        if (commerceAdapter.getCatalogAdapter() != null) {
            capabilities.put("dev.ucp.shopping.catalog",
                    List.of(new CapabilityDetail(ucpVersion, "https://ucp.dev/specification/catalog")));
        }
        if (commerceAdapter.getCartAdapter() != null) {
            capabilities.put("dev.ucp.shopping.cart",
                    List.of(new CapabilityDetail(ucpVersion, "https://ucp.dev/specification/cart")));
        }
        if (commerceAdapter.getOrderAdapter() != null) {
            capabilities.put("dev.ucp.shopping.order",
                    List.of(new CapabilityDetail(ucpVersion, "https://ucp.dev/specification/order")));
        }
        if (commerceAdapter.getCustomerAdapter() != null) {
            capabilities.put("dev.ucp.common.identity",
                    List.of(new CapabilityDetail(ucpVersion, "https://ucp.dev/specification/identity")));
        }

        return new DiscoveryResponse(new UcpInfo(ucpVersion, capabilities));
    }

    /**
     * Top-level response wrapper for UCP discovery.
     */
    public record DiscoveryResponse(UcpInfo ucp) {
    }

    /**
     * Detailed UCP protocol information.
     * 
     * @param version      The protocol version (e.g., 2026-01-23).
     * @param capabilities A map of capability keys (namespace) to their details.
     */
    public record UcpInfo(String version, Map<String, List<CapabilityDetail>> capabilities) {
    }

    /**
     * Details about a specific capability interaction.
     * 
     * @param version The version of the capability spec supported.
     * @param spec    The URL to the official UCP specification for this capability.
     */
    public record CapabilityDetail(String version, String spec) {
    }
}
