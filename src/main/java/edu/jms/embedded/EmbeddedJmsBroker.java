package edu.jms.embedded;

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.hooks.SpringContextHook;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Starts an embedded activeMQ message broker with in-memory storage.
 */
@SpringBootApplication
public class EmbeddedJmsBroker {

    @Value("${broker.url}")
    private String jmsBrokerUrl;

    @Value("${broker.name}")
    private String name;

    @Value("${broker.auth.username}")
    private String username;

    @Value("${broker.auth.password}")
    private String password;

    @Bean
    public BrokerService brokerService() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName(name);
        broker.addConnector(jmsBrokerUrl);
        broker.setPersistent(false);

        final SimpleAuthenticationPlugin authenticationPlugin = new SimpleAuthenticationPlugin();
        authenticationPlugin.setAnonymousAccessAllowed(false);
        authenticationPlugin.setUsers(Arrays.asList(new AuthenticationUser(username, password, "")));
        broker.setPlugins(new BrokerPlugin[]{authenticationPlugin});

        broker.addShutdownHook(new SpringContextHook());

        broker.start();

        return broker;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedJmsBroker.class, args);
    }
}
