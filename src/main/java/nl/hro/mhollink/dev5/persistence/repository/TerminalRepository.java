package nl.hro.mhollink.dev5.persistence.repository;

import nl.hro.mhollink.dev5.models.Terminal;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends GraphRepository<Terminal> {


}