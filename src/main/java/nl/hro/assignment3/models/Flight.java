package nl.hro.assignment3.models;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity

public class Flight implements IModel{

    @GraphId
    private Long id;

    private String code;
    private String plane;

    public Flight() {
    }

    public Flight(String code, String plane) {
        this.code = code;
        this.plane = plane;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", plane='" + plane + '\'' +
                '}';
    }
}
