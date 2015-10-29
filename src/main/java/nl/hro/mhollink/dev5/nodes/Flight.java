package nl.hro.mhollink.dev5.nodes;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;


public class Flight extends Nodeable{

    String code, plane;

    public Flight(GraphDatabaseService graphDbS, String code, String plane) {
        this.code = code;
        this.plane = plane;

        createNodeFromObject(graphDbS);
    }

    public void createNodeFromObject(GraphDatabaseService graphDbS) {

        Node node = graphDbS.createNode();

        node.addLabel(() -> "Flight");

        node.setProperty("Code", code);
        node.setProperty("Plane", plane);

        this.node =  node;
    }

}
