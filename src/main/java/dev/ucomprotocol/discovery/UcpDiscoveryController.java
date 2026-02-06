package dev.ucomprotocol.discovery;

import dev.ucomprotocol.autoconfigure.UcpProperties;
import dev.ucomprotocol.spi.CommerceAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        List<String> capabilities = new ArrayList<>();
        if (commerceAdapter.getCatalogAdapter() != null)
            capabilities.add("catalog");
        if (commerceAdapter.getCartAdapter() != null)
            capabilities.add("cart");
        if (commerceAdapter.getOrderAdapter() != null)
            capabilities.add("order");
        if (commerceAdapter.getCustomerAdapter() != null)
            capabilities.add("customer");

        return new DiscoveryResponse(
                "1.0",
                ucpProperties.getProvider(),
                capabilities);
    }

    public record DiscoveryResponse(String ucpVersion, String provider, List<String> capabilities) {
    }
}
