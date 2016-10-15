package nl.hro.mhollink.dev5.models.relations;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hro.mhollink.dev5.models.Company;
import nl.hro.mhollink.dev5.models.Flight;
import nl.hro.mhollink.dev5.models.IModel;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type="SELLS")
public class Sells implements IModel {

    @GraphId
    private Long id;

    @Property
    private Double price;

    @StartNode
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Company company;

    @EndNode
    private Flight flight;

    public Sells(double price, Company company, Flight flight) {
        this.price = price;
        this.company = company;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", price=" + price +
                ", company=" + company +
                ", flight=" + flight +
                '}';
    }
}
