package nl.hro.mhollink.dev5.persistence.repository;

import nl.hro.mhollink.dev5.models.Airport;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends GraphRepository<Airport> {

}