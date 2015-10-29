package nl.hro.mhollink.dev5.nodes;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

public class Company extends Nodeable{

    String name, plane_number, asset;

    public Company(GraphDatabaseService graphDb, String name, String plane_number, String asset) {
        this.name = name;
        this.plane_number = plane_number;
        this.asset = asset;

        createNodeFromObject(graphDb);
    }

    public void createNodeFromObject(GraphDatabaseService graphDbS) {

        Node node = graphDbS.createNode();

        node.addLabel(() -> "Company");

        node.setProperty("Name", name);
        node.setProperty("Plane_Number", plane_number);
        node.setProperty("asset", asset);

        this.node = node;
    }
}
