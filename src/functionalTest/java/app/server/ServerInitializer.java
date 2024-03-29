package app.server;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServerInitializer {

    @Value("${api.port}")
    private Integer port;

    @Bean
    public ClientAndServer startMockServer() {
        ConfigurationProperties.logLevel("WARN");

        return startClientAndServer(port);
    }
}
