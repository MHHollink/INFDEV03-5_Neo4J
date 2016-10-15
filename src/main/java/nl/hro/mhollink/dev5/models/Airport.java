package nl.hro.mhollink.dev5.models;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Airport  {

    @GraphId
    Long id;

    private String name;
    private String city;
    private String size;
    private int capacity;

    public Airport(String name, String city, String size, int capacity) {
        this.name = name;
        this.city = city;
        this.size = size;
        this.capacity = capacity;
    }
}
