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
                .andExpect(jsonPath("$.ucpVersion").value("1.0"))
                .andExpect(jsonPath("$.provider").value("mock"))
                .andExpect(jsonPath("$.capabilities").isArray());
    }
}
