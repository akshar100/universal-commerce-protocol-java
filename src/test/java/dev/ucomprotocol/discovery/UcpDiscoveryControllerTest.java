package dev.ucomprotocol.discovery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import dev.ucomprotocol.autoconfigure.UcpAutoConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
public class UcpDiscoveryControllerTest {

    @SpringBootApplication
    @Import(UcpAutoConfiguration.class)
    static class TestConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDiscoveryInfo() throws Exception {
        mockMvc.perform(get("/.well-known/ucp"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ucp.version").value("2026-01-23"))
                .andExpect(jsonPath("$.ucp.capabilities").isMap())
                .andExpect(jsonPath("$.ucp.capabilities['dev.ucp.shopping.catalog']").isArray())
                .andExpect(jsonPath("$.ucp.capabilities['dev.ucp.shopping.cart']").isArray());
    }
}
