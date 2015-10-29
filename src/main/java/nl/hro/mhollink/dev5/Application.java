package nl.hro.mhollink.dev5;

import nl.hro.mhollink.dev5.Relations.Relationshipper;
import nl.hro.mhollink.dev5.nodes.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Application {

    // TODO: MAKE THEM STRINGS MORE PROJECT BASED!
    private static final String DB_PATH = "/database/Neo4j/aeroplano.graphdb";
    private static final String JSON_PATH = "/nodes.json";

    GraphDatabaseService graphDb;


    public static void main(String[] args) {
        new Application();
    }

    public Application() {

        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        registerShutdownHook(graphDb);


        try ( Transaction tx = graphDb.beginTx() )
        {
            JSONObject file = (JSONObject) new JSONParser().parse( new FileReader( JSON_PATH ) );

            JSONArray companiesJSON = (JSONArray) file.get("companies");
            for (Object aCompaniesJSON : companiesJSON) {
                JSONObject company = (JSONObject) aCompaniesJSON;

                new Company(graphDb, (String) company.get("name"), (String) company.get("plane_number"), (String) company.get("asset"));
            }


            JSONArray airfieldJSON = (JSONArray) file.get("airfields");
            for (Object airportJsonObject : airfieldJSON) {
                JSONObject airport = (JSONObject) airportJsonObject;
                Airport port = new Airport(graphDb, (String) airport.get("name"),(String) airport.get("city"),(String) airport.get("size"),  (int)(long) airport.get("capacity") );

                JSONArray terminalJSON = (JSONArray) airport.get("terminals");
                for (Object terminalJsonObject : terminalJSON) {
                    JSONObject terminal = (JSONObject) terminalJsonObject;
                    Terminal term = new Terminal(graphDb, (String) terminal.get("code"), (String) terminal.get("status"));

                    new Relationshipper(port.getNode(), term.getNode()).createRelationship("INCLUDES");

                    JSONArray gateJSON = (JSONArray) terminal.get("gates");
                    for (Object gateJsonObject : gateJSON) {
                        JSONObject gate = (JSONObject) gateJsonObject;

                        Gate g = new Gate(graphDb, (String) gate.get("number"), (String) gate.get("state"));

                        new Relationshipper(term.getNode(), g.getNode()).createRelationship("BELONGS");
                    }

                    Node node = graphDb.findNode(() -> "Company", "Name", (String) terminal.get("company"));
                    new Relationshipper(node, term.getNode()).createRelationship("BASED");

                }

                JSONArray flightJSON = (JSONArray) airport.get("flights");
                for (Object flightJsonObject : flightJSON) {
                    JSONObject flight = (JSONObject) flightJsonObject;
                    Flight fly = new Flight(graphDb, (String) flight.get("code"), (String) flight.get("plane"));

                    new Relationshipper(port.getNode(), fly.getNode()).createRelationship("TRAVELS");

                    Node node = graphDb.findNode(() -> "Company", "Plane_Number", ((String) flight.get("plane")).split("-")[0]);
                    new Relationshipper(node, fly.getNode()).createRelationship("SELLS", new String[][]{{"Price", String.valueOf(new Random().nextInt(419)+80)}});
                }

            }

            tx.success();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }


    private void registerShutdownHook(GraphDatabaseService graphDb) {

    }

    private enum RelationTypes implements RelationshipType {
        TRAVELS, SELLS, INCLUDES, BELONGS, BASED
    }
}
