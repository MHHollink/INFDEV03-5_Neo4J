package nl.hro.assignment3.persistence;

import nl.hro.assignment3.Application;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "nl.hro.assignment3.persistence.repository")
public class PersistenceContext extends Neo4jConfiguration {

    @Bean
    @SuppressWarnings("WeakerAccess")
    public org.neo4j.ogm.config.Configuration getConfig() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration();

        configuration.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setCredentials(Application.neo4jUser, Application.neo4jPassword)
                .setURI("http://localhost:7474");

        return configuration;
    }

    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(getConfig(), "nl.hro.mhollink.dev5.models");
    }
}