package nl.hro.mhollink.dev5.rest;

import nl.hro.mhollink.dev5.models.Airport;
import nl.hro.mhollink.dev5.models.Flight;
import nl.hro.mhollink.dev5.models.Terminal;
import nl.hro.mhollink.dev5.models.relations.Travels;
import nl.hro.mhollink.dev5.persistence.repository.AirportRepository;
import nl.hro.mhollink.dev5.rest.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

/**
 * Created by marcel on 15-10-2016.
 */
@RestController
@RequestMapping(value = "/airports")
public class AirportController {

    @Autowired
    private AirportRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(airports::add);
        return ResponseEntity.ok(airports);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Airport> createAirport(@RequestBody(required = false) Airport airport) {

        if (airport == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        if(airport.getName() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        if(airport.getCity() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        return ResponseEntity.ok(repository.save(airport));
    }

    @RequestMapping(value = "{id}/terminals", method = RequestMethod.PUT)
    public ResponseEntity<Airport> addTerminal(@PathVariable("id") long id,
                                               @RequestBody(required = false) Terminal terminal) {
        Airport airport = repository.findOne(id);

        if (airport == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (terminal == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (terminal.getCode() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (!Objects.equals(terminal.getStatus(), "OPEN") || !Objects.equals(terminal.getStatus(), "CLOSED"))
            terminal.setStatus("CLOSED");

        Set<Terminal> terminals = airport.getTerminals();
        if (terminals == null) // First to add, create set!
            terminals = new HashSet<>();
        terminals.add(terminal);

        airport.setTerminals(terminals);

        return ResponseEntity.ok(repository.save(airport));
    }

    @RequestMapping(value = "{id}/flights", method = RequestMethod.PUT)
    public ResponseEntity<Airport> addFlight(@PathVariable("id") long id,
                                             @RequestBody(required = false) FlightDTO flight) {
        Airport airport = repository.findOne(id);

        if (airport == null)
            return new ResponseEntityFactory<Airport>().generateBadRequestEntity("ID unknown");

        if (flight == null)
            return new ResponseEntityFactory<Airport>().generateBadRequestEntity("Missing request body");

        if (flight.getCode() == null
                || flight.getPlane() == null
                || flight.getDistance() == null
                || flight.getTime() == null
                || flight.getFrom() == null )
            return new ResponseEntityFactory<Airport>().generateBadRequestEntity("Missing parameters, given object: %s", flight);

        Travels relation = new Travels(
                flight.getFrom(),
                flight.getDistance(),
                flight.getTime(),
                airport,
                new Flight(
                        flight.getCode(),
                        flight.getPlane()
                )
        );

        Set<Travels> flights = airport.getFlights();
        if (flights == null) // First to add, create set!
            flights = new HashSet<>();
        flights.add(relation);

        airport.setFlights(flights);

        return ResponseEntity.ok(repository.save(airport));
    }



}
