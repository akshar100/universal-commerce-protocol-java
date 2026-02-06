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

@AutoConfiguration
@EnableConfigurationProperties(UcpProperties.class)
public class UcpAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(UcpAutoConfiguration.class);

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

    @Bean
    @ConditionalOnWebApplication
    public dev.ucomprotocol.discovery.UcpDiscoveryController ucpDiscoveryController(UcpProperties properties,
            CommerceAdapter adapter) {
        return new dev.ucomprotocol.discovery.UcpDiscoveryController(properties, adapter);
    }
}
