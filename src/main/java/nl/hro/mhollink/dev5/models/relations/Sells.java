package nl.hro.mhollink.dev5.models.relations;

import nl.hro.mhollink.dev5.models.Company;
import nl.hro.mhollink.dev5.models.Flight;
import nl.hro.mhollink.dev5.models.Terminal;
import org.neo4j.ogm.annotation.*;

/**
 * Created by marcel on 15-10-2016.
 */
@RelationshipEntity(type="SELLS")
public class Sells {

    @GraphId
    private Long relationshipId;

    @Property
    private double price;

    @StartNode
    private Company company;

    @EndNode
    private Flight flight;

    public Sells(double price, Company company, Flight flight) {
        this.price = price;
        this.company = company;
        this.flight = flight;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Sells{" +
                "relationshipId=" + relationshipId +
                ", price=" + price +
                ", company=" + company +
                ", flight=" + flight +
                '}';
    }
}
