package nl.hro.mhollink.dev5.nodes;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;


public class Terminal extends Nodeable{

    String code, status;

    public Terminal(GraphDatabaseService graphDbS, String code, String status) {
        this.code = code;
        this.status = status;

        createNodeFromObject(graphDbS);
    }

    public void createNodeFromObject(GraphDatabaseService graphDbS) {

        Node node = graphDbS.createNode();

        node.addLabel(() -> "Terminal");

        node.setProperty("Code", code);
        node.setProperty("Status", status);

        this.node = node;
    }
}
