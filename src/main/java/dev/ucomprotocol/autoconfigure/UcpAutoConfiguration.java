package dev.ucomprotocol.autoconfigure;

import dev.ucomprotocol.spi.CommerceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * Auto-configuration for the Universal Commerce Protocol (UCP) library.
 * <p>
 * This configuration:
 * <ul>
 * <li>Enables UCP properties binding.</li>
 * <li>Configures a default {@link CommerceAdapter} if none is provided.</li>
 * <li>Registers the {@link dev.ucomprotocol.discovery.UcpDiscoveryController}
 * for capability discovery.</li>
 * </ul>
 */
@AutoConfiguration
@EnableConfigurationProperties(UcpProperties.class)
public class UcpAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(UcpAutoConfiguration.class);

    /**
     * Provides a default Mock Commerce Adapter if no other adapter is present
     * and the provider property is set to "mock" (default).
     *
     * @return a mock implementation of {@link CommerceAdapter}
     */
    @Bean
    @ConditionalOnMissingBean(CommerceAdapter.class)
    @ConditionalOnProperty(name = "ucp.provider", havingValue = "mock", matchIfMissing = true)
    public CommerceAdapter mockCommerceAdapter() {
        logger.info("Configuring Mock Commerce Adapter");
        // In a real scenario, this would return a full implementation.
        // For now, we return null or a simple proxy to avoid compilation errors if no
        // implementation exists yet.
        // We will implement a basic MockCommerceAdapter inline or in a separate file
        // for the library to function.
        return new dev.ucomprotocol.spi.mock.MockCommerceAdapter();
    }

    /**
     * Registers the UCP Discovery Controller.
     *
     * @param properties the UCP configuration properties
     * @param adapter    the configured commerce adapter
     * @return the controller instance
     */
    @Bean
    @ConditionalOnWebApplication
    public dev.ucomprotocol.discovery.UcpDiscoveryController ucpDiscoveryController(UcpProperties properties,
            CommerceAdapter adapter) {
        return new dev.ucomprotocol.discovery.UcpDiscoveryController(properties, adapter);
    }
}
