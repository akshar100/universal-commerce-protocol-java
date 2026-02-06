package dev.ucomprotocol.autoconfigure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class UcpPropertiesTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(UcpAutoConfiguration.class));

    @Test
    void shouldBindProperties() {
        contextRunner
                .withPropertyValues(
                        "ucp.provider=shopify",
                        "ucp.api-key=my-key",
                        "ucp.api-secret=my-secret",
                        "ucp.endpoint=https://myshop.com")
                .run(context -> {
                    assertThat(context).hasSingleBean(UcpProperties.class);
                    UcpProperties properties = context.getBean(UcpProperties.class);
                    assertThat(properties.getProvider()).isEqualTo("shopify");
                    assertThat(properties.getApiKey()).isEqualTo("my-key");
                    assertThat(properties.getApiSecret()).isEqualTo("my-secret");
                    assertThat(properties.getEndpoint()).isEqualTo("https://myshop.com");
                });
    }

    @Test
    void shouldHaveDefaultProviderAsMock() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(UcpProperties.class);
            UcpProperties properties = context.getBean(UcpProperties.class);
            assertThat(properties.getProvider()).isEqualTo("mock");
        });
    }
}
