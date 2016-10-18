package nl.hro.assignment3.persistence.repository;

import nl.hro.assignment3.models.Gate;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends GraphRepository<Gate> {


}