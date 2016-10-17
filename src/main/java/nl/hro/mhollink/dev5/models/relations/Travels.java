package nl.hro.mhollink.dev5.models.relations;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hro.mhollink.dev5.models.Airport;
import nl.hro.mhollink.dev5.models.Flight;
import nl.hro.mhollink.dev5.models.IModel;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="TRAVELS")
public class Travels implements IModel {

    @GraphId
    private Long id;

    @Property
    private String from;
    @Property
    private Double distance;
    @Property
    private Long time;

    @StartNode
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Airport airport;

    @EndNode
    private Flight flight;

    public Travels() {
    }

    public Travels(String from, double distance, long time, Airport airport, Flight flight) {
        this.from = from;
        this.distance = distance;
        this.time = time;
        this.airport = airport;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Travels{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", distance=" + distance +
                ", time=" + time +
                ", airport=" + airport +
                ", flight=" + flight +
                '}';
    }
}
