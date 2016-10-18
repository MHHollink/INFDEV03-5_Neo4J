package nl.hro.assignment3.persistence.repository;

import nl.hro.assignment3.models.Company;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends GraphRepository<Company> {

    @Query("MATCH (c:Company {name:{0}}) RETURN c;")
    Company getCompanyByName(String name);

}