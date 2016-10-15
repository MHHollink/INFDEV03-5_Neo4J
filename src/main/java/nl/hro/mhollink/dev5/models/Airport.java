package nl.hro.mhollink.dev5.models;

import nl.hro.mhollink.dev5.models.relations.Travels;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Airport implements IModel {

    @GraphId
    private Long id;

    private String name;
    private String city;
    private String size;
    private Integer capacity;

    @Relationship(type = "INCLUDES")
    private Set<Terminal> terminals;

    @Relationship(type = "TRAVELS")
    private Set<Travels> flights;

    public Airport() {
    }

    public Airport(String name, String city, String size, int capacity) {
        this.name = name;
        this.city = city;
        this.size = size;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(Set<Terminal> terminals) {
        this.terminals = terminals;
    }

    public Set<Travels> getFlights() {
        return flights;
    }

    public void setFlights(Set<Travels> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", size='" + size + '\'' +
                ", capacity=" + capacity +
                ", terminals=" + terminals +
                ", flights=" + flights +
                '}';
    }
}
