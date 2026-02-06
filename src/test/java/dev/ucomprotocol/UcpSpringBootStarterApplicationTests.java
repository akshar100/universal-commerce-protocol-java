package dev.ucomprotocol;

import dev.ucomprotocol.autoconfigure.UcpAutoConfiguration;
import dev.ucomprotocol.spi.CommerceAdapter;
import dev.ucomprotocol.spi.mock.MockCommerceAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UcpAutoConfiguration.class)
class UcpSpringBootStarterApplicationTests {

    @Autowired
    private CommerceAdapter commerceAdapter;

    @Test
    void contextLoads() {
        assertThat(commerceAdapter).isNotNull();
        assertThat(commerceAdapter).isInstanceOf(MockCommerceAdapter.class);
        assertThat(commerceAdapter.getCatalogAdapter().getProductById("123")).isPresent();
    }
}
