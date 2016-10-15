package nl.hro.mhollink.dev5.models;

import nl.hro.mhollink.dev5.models.relations.Sells;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Company implements IModel {

    @GraphId
    private Long id;

    private String name;
    private String plane_number;
    private String asset;

    @Relationship(type = "SELLS")
    private Set<Sells> flights;

    public Company() {
    }

    public Company(String name, String plane_number, String asset) {
        this.name = name;
        this.plane_number = plane_number;
        this.asset = asset;
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

    public String getPlane_number() {
        return plane_number;
    }

    public void setPlane_number(String plane_number) {
        this.plane_number = plane_number;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Set<Sells> getFlights() {
        return flights;
    }

    public void setFlights(Set<Sells> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plane_number='" + plane_number + '\'' +
                ", asset='" + asset + '\'' +
                ", flights=" + flights +
                '}';
    }
}
