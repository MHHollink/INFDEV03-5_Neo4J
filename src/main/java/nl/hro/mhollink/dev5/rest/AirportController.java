package nl.hro.mhollink.dev5.rest;

import nl.hro.mhollink.dev5.models.Airport;
import nl.hro.mhollink.dev5.persistence.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marcel on 15-10-2016.
 */
@RestController
@RequestMapping(value = "/airports")
public class AirportController {

    @Autowired
    private AirportRepository repository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Airport getAirportByID(@PathVariable long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public Airport CreateAirport(){
        return repository.save(new Airport("Schiphol", "Amsterdam", "XXL", 26000));
    }
}
