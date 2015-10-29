package nl.hro.mhollink.dev5.nodes;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

public class Airport extends Nodeable{

    private String name, city, size;
    private int capacity;

    public Airport(GraphDatabaseService graphDbS, String name, String city, String size, int capacity) {
        this.name = name;
        this.city = city;
        this.size = size;
        this.capacity = capacity;

        createNodeFromObject(graphDbS);
    }

    public void createNodeFromObject(GraphDatabaseService graphDbS) {

        Node node = graphDbS.createNode();

        node.addLabel(() -> "Airport");

        node.setProperty("Name", name);
        node.setProperty("City", city);
        node.setProperty("Capacity", capacity);
        node.setProperty("Size", size);

        this.node = node;
    }
}
