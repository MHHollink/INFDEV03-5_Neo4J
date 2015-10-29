package nl.hro.mhollink.dev5.nodes;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;


public class Gate extends Nodeable{

    String number, state;

    public Gate(GraphDatabaseService graphDbS, String number, String state) {
        this.number = number;
        this.state = state;

        createNodeFromObject(graphDbS);
    }

    public void createNodeFromObject(GraphDatabaseService graphDbS) {

        Node node = graphDbS.createNode();

        node.addLabel(() -> "Gate");

        node.setProperty("Number", number);
        node.setProperty("State", state);

        this.node =  node;
    }

}
