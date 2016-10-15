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

    public static String neo4jUser;
    public static String neo4jPassword;

    public static void main(String[] args) {

        if(args.length != 2)
            return;

        neo4jUser       = args[0];
        neo4jPassword   = args[1];

        SpringApplication.run(Application.class, args);
    }

}
