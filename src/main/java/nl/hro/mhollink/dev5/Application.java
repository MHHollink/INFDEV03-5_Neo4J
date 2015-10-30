package nl.hro.mhollink.dev5;

import nl.hro.mhollink.dev5.relations.Relationshipper;
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
import java.util.Date;
import java.util.Random;

/**
 * Java application for loading in a bunch of nodes in a Neo4J GraphDatabase.
 * The nodes are loaded from a nodes.json file in the resource folder.
 *
 * @author Marcel Hollink
 * @version 0.0.3
 * @since 29-30-2015
 */
public class Application {

    private static final String DB_PATH = "database/Neo4j/aeroplano.graphdb";       // FILE LOCATION FOR THE DATABASE
    private static final String JSON_PATH = "nodes.json";                           // LOCATION FOR THE JSON FILE

    public static void main(String[] args) {
        new Application();
    }

    public Application() {
        // Get the base url for where the files are located on the computer
        final String BASE = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6);

        // create a GraphDatabase on the location (if this location is empty, the db will be created)
        // noinspection deprecation
        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(BASE + DB_PATH);

        // try to start an transaction
        try ( Transaction tx = graphDb.beginTx() )
        {
            // Load the Json file as an JSONObject from the json-simple lib.
            log("loading file from "+BASE+JSON_PATH);
            JSONObject file = (JSONObject) new JSONParser().parse(new FileReader(BASE + JSON_PATH));

            // load all the company objects in a json array
            log("loading companies...");
            JSONArray companiesJSON = (JSONArray) file.get("companies");

            // foreach company create a node.
            for (Object CompanyJSON : companiesJSON) {
                // load a single company into an json object
                JSONObject company = (JSONObject) CompanyJSON;

                // log the data inside of the json object
                log(company.toJSONString());

                // create an new company node
                new Company(graphDb, (String) company.get("name"), (String) company.get("plane_number"), (String) company.get("asset"));
            }

            // load all airfields in a json array
            log("loading arfields");
            JSONArray airfieldJSON = (JSONArray) file.get("airfields");

            // foreach airfield create a node and load all his child nodes
            for (Object airportJsonObject : airfieldJSON) {
                // load a single airport into a json object
                JSONObject airport = (JSONObject) airportJsonObject;

                // create a new airport node
                // save the airport object to register its relations to his child nodes (terminals)
                Airport port = new Airport(graphDb, (String) airport.get("name"),(String) airport.get("city"),(String) airport.get("size"),  (int)(long) airport.get("capacity") );

                // log the data in this airfield object
                log(airport.toJSONString());

                // load all terminals for the given airport
                log("loading terminals on "+airport.get("name"));
                JSONArray terminalJSON = (JSONArray) airport.get("terminals");

                // foreach terminal create a node
                for (Object terminalJsonObject : terminalJSON) {
                    // load a single terminal into a json object
                    JSONObject terminal = (JSONObject) terminalJsonObject;

                    // log the data inside this json object
                    log(terminal.toJSONString());

                    // create the terminal node
                    // save the terminal object to register its relationship to the child nodes (gates)
                    Terminal term = new Terminal(graphDb, (String) terminal.get("code"), (String) terminal.get("status"));

                    // generate a relationship between the current airport and the terminal, the relation should be of type INCLUDES
                    new Relationshipper(port.getNode(), term.getNode()).createRelationship(String.valueOf(RelationTypes.INCLUDES));

                    // load all gates for the given terminal
                    log("loading gates on "+airport.get("name")+" by terminal "+terminal.get("code"));
                    JSONArray gateJSON = (JSONArray) terminal.get("gates");

                    // for each gate create a node
                    for (Object gateJsonObject : gateJSON) {
                        // load a single gate into a json object
                        JSONObject gate = (JSONObject) gateJsonObject;

                        // log the data inside of this json object
                        log(gate.toJSONString());

                        // create a gate node
                        // save the gate object to register its relationship to the parent node (terminal)
                        Gate g = new Gate(graphDb, (String) gate.get("number"), (String) gate.get("state"));

                        // generate a relationship between the current terminal and the gate, the relation should be of type BELONGS
                        new Relationshipper(term.getNode(), g.getNode()).createRelationship(String.valueOf(RelationTypes.BELONGS));
                    }

                    // get the Company node from the GraphDatabase which is the owner of the terminal
                    Node node = graphDb.findNode(() -> "Company", "Name", terminal.get("company"));

                    // generate the relation between the terminal and the Company, the relation should be of type BASED and should have the properties 'rent' and 'since'
                    new Relationshipper(node, term.getNode()).createRelationship(String.valueOf(RelationTypes.BASED), new String[][]{{"rent", String.valueOf(new Random().nextInt(419)+80)},{"since", String.valueOf(new Random().nextInt(419)+80)}});

                }

                // load all the flights for the given airport
                log("loading flights on "+airport.get("name"));
                JSONArray flightJSON = (JSONArray) airport.get("flights");

                // foreach flight create a flight node
                for (Object flightJsonObject : flightJSON) {
                    // load a single flight into a json object
                    JSONObject flight = (JSONObject) flightJsonObject;

                    // log the data inside the flight object
                    log(flight.toJSONString());

                    // create the flight node
                    // save the flight object to register its relationship to the parent Airport
                    Flight fly = new Flight(graphDb, (String) flight.get("code"), (String) flight.get("plane"));

                    // generate the relation between the current airport and the flight, the relation should be of type TRAVELS and should have the properties 'from', 'distance', and 'time'
                    new Relationshipper(port.getNode(), fly.getNode()).createRelationship(String.valueOf(RelationTypes.TRAVELS), new String[][]{{"from", String.valueOf(new Random().nextInt(419)+80)},{"distance", String.valueOf(new Random().nextInt(419)+80)},{"time", String.valueOf(new Random().nextInt(419)+80)}});

                    // get the Company node from the GraphDatabase which sells the current flight
                    Node node = graphDb.findNode(() -> "Company", "Plane_Number", ((String) flight.get("plane")).split("-")[0]);

                    // generate the relation between the company and the flight, the relation should be of type SELLS and should have the property price
                    new Relationshipper(node, fly.getNode()).createRelationship(String.valueOf(RelationTypes.SELLS), new String[][]{{"Price", String.valueOf(new Random().nextInt(419)+80)}});
                }

            }
            // save the transaction to the GraphDatabase
            tx.success();

        } catch (ParseException | IOException e) {
            // Exceptions from Loading the files or Parsing the json object
            e.printStackTrace();
        }
    }

    // Relation
    private enum RelationTypes implements RelationshipType {
        TRAVELS, SELLS, INCLUDES, BELONGS, BASED
    }

    private void log(String s) {
        // noinspection deprecation
        System.out.println(new Date().toLocaleString() + " :: "+ s);
    }
}
