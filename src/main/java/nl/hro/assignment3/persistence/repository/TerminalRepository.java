package nl.hro.assignment3.persistence.repository;

import nl.hro.assignment3.models.Terminal;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends GraphRepository<Terminal> {


}