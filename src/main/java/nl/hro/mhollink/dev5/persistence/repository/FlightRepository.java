package nl.hro.mhollink.dev5.persistence.repository;

import nl.hro.mhollink.dev5.models.Flight;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends GraphRepository<Flight> {

    List<Flight> findAll();

}