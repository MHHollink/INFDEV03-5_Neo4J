package nl.hro.mhollink.dev5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Java application for loading in a bunch of models in a Neo4J GraphDatabase.
 *
 * @author Marcel Hollink
 * @version 0.0.3
 * @since 14-10-2015
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
