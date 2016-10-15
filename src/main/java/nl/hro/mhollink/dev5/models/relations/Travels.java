package nl.hro.mhollink.dev5.models.relations;

import nl.hro.mhollink.dev5.models.Airport;
import nl.hro.mhollink.dev5.models.Flight;
import org.neo4j.ogm.annotation.*;

/**
 * Created by marcel on 15-10-2016.
 */
@RelationshipEntity(type="TRAVELS")
public class Travels {

    @GraphId
    private Long relationshipId;

    @Property
    private String from;
    @Property
    private double distance;
    @Property
    private long time;

    @StartNode
    private Airport airport;
    @EndNode
    private Flight flight;

    public Travels(String from, double distance, long time, Airport airport, Flight flight) {
        this.from = from;
        this.distance = distance;
        this.time = time;
        this.airport = airport;
        this.flight = flight;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
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
                "relationshipId=" + relationshipId +
                ", from='" + from + '\'' +
                ", distance=" + distance +
                ", time=" + time +
                ", airport=" + airport +
                ", flight=" + flight +
                '}';
    }
}
