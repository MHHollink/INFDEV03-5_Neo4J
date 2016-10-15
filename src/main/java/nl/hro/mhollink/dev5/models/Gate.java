package nl.hro.mhollink.dev5.models;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Gate implements IModel{

    @GraphId
    private Long id;

    private String number;
    private String state;

    public Gate() {
    }

    public Gate(String number, String state) {
        this.number = number;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Gate{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
