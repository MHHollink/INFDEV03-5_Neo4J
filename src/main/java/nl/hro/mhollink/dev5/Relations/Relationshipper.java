package nl.hro.mhollink.dev5.relations;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;


public class Relationshipper {

    Node a, b;

    public Relationshipper(Node a, Node b) {
        this.a = a;
        this.b = b;
    }

    public Relationship createRelationship (String type){

        return a.createRelationshipTo(b, () -> type);

    }

    public Relationship createRelationship (String type, String[][] properties ){

        Relationship relationship = a.createRelationshipTo(b, () -> type);

        for (String[] property : properties) {
            relationship.setProperty(property[0], property[1]);
        }

        return relationship;

    }
}
