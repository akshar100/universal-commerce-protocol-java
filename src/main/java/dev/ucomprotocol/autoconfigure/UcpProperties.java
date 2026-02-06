package dev.ucomprotocol.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ucp")
public class UcpProperties {
    /**
     * The commerce provider to use (e.g., mock, shopify).
     */
    private String provider = "mock";

    /**
     * API key for the commerce provider.
     */
    private String apiKey;

    /**
     * API secret or password for the commerce provider.
     */
    private String apiSecret;

    /**
     * Endpoint URL for the commerce provider.
     */
    private String endpoint;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
