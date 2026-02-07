package dev.ucomprotocol.discovery;

import dev.ucomprotocol.autoconfigure.UcpProperties;
import dev.ucomprotocol.spi.CommerceAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<Capability> capabilities = new ArrayList<>();
        String ucpVersion = "2026-01-23";

        if (commerceAdapter.getCatalogAdapter() != null) {
            capabilities.add(
                    new Capability("dev.ucp.shopping.catalog", ucpVersion, "https://ucp.dev/specification/catalog"));
        }
        if (commerceAdapter.getCartAdapter() != null) {
            capabilities.add(new Capability("dev.ucp.shopping.cart", ucpVersion, "https://ucp.dev/specification/cart"));
        }
        if (commerceAdapter.getOrderAdapter() != null) {
            capabilities
                    .add(new Capability("dev.ucp.shopping.order", ucpVersion, "https://ucp.dev/specification/order"));
        }
        if (commerceAdapter.getCustomerAdapter() != null) {
            capabilities.add(
                    new Capability("dev.ucp.common.identity", ucpVersion, "https://ucp.dev/specification/identity"));
        }

        return new DiscoveryResponse(new UcpInfo(ucpVersion, capabilities, Collections.emptyList()));
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
     * @param capabilities A list of supported capabilities.
     * @param services     A list of available services (endpoints).
     */
    public record UcpInfo(String version, List<Capability> capabilities, List<Service> services) {
    }

    /**
     * Details about a specific capability.
     * 
     * @param type    The capability namespace/type.
     * @param version The version of the capability spec supported.
     * @param spec    The URL to the official UCP specification for this capability.
     */
    public record Capability(String type, String version, String spec) {
    }

    /**
     * Details about a service endpoint.
     * (Placeholder for now as no services are strictly defined in this discovery
     * view yet)
     */
    public record Service(String id, String type, String endpoint) {
    }
}
