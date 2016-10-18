package nl.hro.assignment3.models.relations;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hro.assignment3.models.Company;
import nl.hro.assignment3.models.IModel;
import nl.hro.assignment3.models.Terminal;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="BASED")
public class Based implements IModel {

    @GraphId
    private Long id;

    @Property
    private String rent;
    @Property
    private String since;


    @StartNode
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Terminal terminal;

    @EndNode
    private Company company;

    public Based() {
    }

    public Based(String rent, String since, Terminal terminal, Company company) {
        this.rent = rent;
        this.since = since;
        this.terminal = terminal;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Based{" +
                "id=" + id +
                ", rent='" + rent + '\'' +
                ", since='" + since + '\'' +
                ", terminal=" + terminal +
                ", company=" + company +
                '}';
    }
}
