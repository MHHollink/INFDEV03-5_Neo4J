package nl.hro.mhollink.dev5.models.relations;

import nl.hro.mhollink.dev5.models.Airport;
import nl.hro.mhollink.dev5.models.Company;
import nl.hro.mhollink.dev5.models.Flight;
import nl.hro.mhollink.dev5.models.Terminal;
import org.neo4j.ogm.annotation.*;

/**
 * Created by marcel on 15-10-2016.
 */
@RelationshipEntity(type="BASED")
public class Based {

    @GraphId
    private Long relationshipId;

    @Property
    private String rent;
    @Property
    private String since;


    @StartNode
    private Terminal terminal;
    @EndNode
    private Company company;

    public Based(String rent, String since, Terminal terminal, Company company) {
        this.rent = rent;
        this.since = since;
        this.terminal = terminal;
        this.company = company;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
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
                "relationshipId=" + relationshipId +
                ", rent='" + rent + '\'' +
                ", since='" + since + '\'' +
                ", terminal=" + terminal +
                ", company=" + company +
                '}';
    }
}
