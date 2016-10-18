package nl.hro.assignment3.persistence.repository;

import nl.hro.assignment3.models.Airport;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends GraphRepository<Airport> {

//    @Query("MATCH (a:Airport{size:{0}}) RETURN a.name, a.capacity;")
//    List<Airport> getAirportBySize(String size);

}