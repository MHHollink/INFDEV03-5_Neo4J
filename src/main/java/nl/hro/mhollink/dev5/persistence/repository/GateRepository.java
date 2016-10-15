package nl.hro.mhollink.dev5.persistence.repository;

import nl.hro.mhollink.dev5.models.Gate;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends GraphRepository<Gate> {


}