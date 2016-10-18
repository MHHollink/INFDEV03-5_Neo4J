package nl.hro.assignment3.rest;

import nl.hro.assignment3.models.Airport;
import nl.hro.assignment3.persistence.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


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

//    @RequestMapping(value = "search/{size}", method = RequestMethod.GET)
//    public ResponseEntity<List<Airport>> getAirportsOfSize(@PathVariable("size") String size) {
//
//        if (size == null)
//            return new ResponseEntityFactory<List<Airport>>().generateBadRequestEntity("Missing body parameter 'size'");
//
//        return new ResponseEntity<>(repository.getAirportBySize(size), HttpStatus.OK);
//    }


}
