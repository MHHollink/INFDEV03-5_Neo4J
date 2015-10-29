package nl.hro.mhollink.dev5.nodes;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

abstract class Nodeable {

    protected Node node;

    protected abstract void createNodeFromObject(GraphDatabaseService graphDbS);

    public Node getNode() {
        return node;
    }
}
