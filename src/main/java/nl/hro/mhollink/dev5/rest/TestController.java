package nl.hro.mhollink.dev5.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import nl.hro.mhollink.dev5.models.Airport;
import nl.hro.mhollink.dev5.models.Company;
import nl.hro.mhollink.dev5.models.Flight;
import nl.hro.mhollink.dev5.models.Gate;
import nl.hro.mhollink.dev5.models.Terminal;
import nl.hro.mhollink.dev5.models.relations.Sells;
import nl.hro.mhollink.dev5.models.relations.Travels;
import nl.hro.mhollink.dev5.persistence.repository.AirportRepository;
import nl.hro.mhollink.dev5.persistence.repository.CompanyRepository;
import nl.hro.mhollink.dev5.persistence.repository.FlightRepository;
import nl.hro.mhollink.dev5.persistence.repository.GateRepository;
import nl.hro.mhollink.dev5.persistence.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired private AirportRepository  airportRepository;
    @Autowired private CompanyRepository  companyRepository;
    @Autowired private FlightRepository   flightRepository;
    @Autowired private GateRepository     gateRepository;
    @Autowired private TerminalRepository terminalRepository;

    @RequestMapping(value = "/reload")
    public ResponseEntity reload() {

        airportRepository.deleteAll();
        companyRepository.deleteAll();
        flightRepository.deleteAll();
        gateRepository.deleteAll();
        terminalRepository.deleteAll();

        return load();
    }

    @SuppressWarnings("WeakerAccess")
    @RequestMapping(value = "/load")
    public ResponseEntity load() {
        JsonObject data;
        try {
            data = (JsonObject) new JsonParser().parse(
                    new FileReader(
                            getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6)
                                    + "nodes.json")
            );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntityFactory().generateBadRequestEntity(e.getMessage());
        }

        for (JsonElement element : data.getAsJsonArray("companies")) {
            Company company = createCompanyFromJson(element.getAsJsonObject());
            companyRepository.save(company);
        }

        for (JsonElement element : data.getAsJsonArray("airfields")) {
            Airport airport = createAirportFromJson(element.getAsJsonObject());
            airportRepository.save(airport);
        }

        companyRepository.findAll().forEach(company -> {
            List<Flight> flights = flightRepository.findAll();
            flights.stream()
                    .filter(flight -> flight.getCode().contains(company.getPlane_number()))
                    .collect(Collectors.toList())
                    .forEach(
                            flight -> {
                Set<Sells> sells = company.getFlights();
                if(sells == null) sells = new HashSet<>();
                sells.add(new Sells(new Random().nextInt(720) + 500 , company, flight));
                company.setFlights(sells);
            });
            companyRepository.save(company);
        });
        return ResponseEntity.ok("");
    }

    private Company createCompanyFromJson(JsonObject object) {
        Company company = new Company();

        company.setName( object.get("name").getAsString() );
        company.setPlane_number( object.get("plane_number").getAsString() );
        company.setAsset( object.get("asset").getAsString() );

        return company;
    }

    private Airport createAirportFromJson(JsonObject object) {
        // Create an empty airport object
        Airport airport = new Airport();

        airport.setName( object.get("name").getAsString() );
        airport.setCity( object.get("city").getAsString() );
        airport.setSize( object.get("size").getAsString() );
        airport.setCapacity( object.get("capacity").getAsInt() );

        Set<Terminal> terminals = new HashSet<>();
        for (JsonElement element : object.getAsJsonArray("terminals")) {
            terminals.add(createTerminalFromJson(element.getAsJsonObject()));
        }
        airport.setTerminals(terminals);

        Set<Travels> flights = new HashSet<>();
        for (JsonElement element : object.getAsJsonArray("flights")) {
            Travels travels = new Travels();
            Flight  flight = new Flight();

            JsonObject obj = element.getAsJsonObject();

            flight.setCode( obj.get("code").getAsString() );
            flight.setPlane(obj.get("plane").getAsString());

            travels.setAirport(airport);
            travels.setFlight(flight);
            travels.setTime(obj.get("time").getAsInt());
            travels.setFrom( obj.get("from").getAsString() );
            travels.setDistance(new Random().nextInt(21000)+480);

            flights.add(travels);
        }
        airport.setFlights(flights);

        return airport;
    }

    private Terminal createTerminalFromJson(JsonObject object) {
        Terminal terminal = new Terminal();

        terminal.setStatus( object.get("status").getAsString() );
        terminal.setCode( object.get("code").getAsString() );

        Company company = companyRepository.getCompanyByName(
                object.get("company").getAsString()
        );

        if (company != null)
            terminal.setCompany(company);

        object.getAsJsonArray("gates").forEach(e -> {
            Set<Gate> gates = terminal.getGates();
            if (gates == null) gates = new HashSet<>();
            gates.add(
                    new Gate(
                            e.getAsJsonObject().get("number").getAsString(),
                            e.getAsJsonObject().get("state").getAsString()
                    )
            );
            terminal.setGates(gates);
        });

        return terminal;
    }




}
