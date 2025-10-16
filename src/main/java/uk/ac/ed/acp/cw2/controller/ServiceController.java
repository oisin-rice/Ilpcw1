package uk.ac.ed.acp.cw2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URL;

import org.springframework.web.server.ResponseStatusException;
import uk.ac.ed.acp.cw2.data.*;
import uk.ac.ed.acp.cw2.service.LocationService;
import uk.ac.ed.acp.cw2.service.RegionService;

/**
 * Controller class that handles various HTTP endpoints for the application.
 * Provides functionality for serving the index page, retrieving a static UUID,
 * and managing key-value pairs through POST requests.
 */
@RestController()
@RequestMapping("/api/v1")
public class ServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Value("${ilp.service.url}")
    public URL serviceUrl;


    @GetMapping("/")
    public String index() {
        return "<html><body>" +
                "<h1>Welcome from ILP</h1>" +
                "<h4>ILP-REST-Service-URL:</h4> <a href=\"" + serviceUrl + "\" target=\"_blank\"> " + serviceUrl+ " </a>" +
                "</body></html>";
    }

    @GetMapping("/uid")
    public String uid() {
        return "s2520345";
    }


    @PostMapping("/distanceTo")
    public double distanceTo(@RequestBody LocationPair locations){
        //input validation - returns 400 status
        if(!LocationService.isValidPair(locations)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid region");
        }
        return LocationService.calcDistance(locations);
    }

    @PostMapping("/isCloseTo")
    public boolean isCloseTo(@RequestBody LocationPair locations){
        if(!LocationService.isValidPair(locations)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid region");
        }
        return LocationService.calcDistance(locations) < 0.00015;
    }

    @PostMapping("/nextPosition")
    public LngLat nextPosition(@RequestBody StartPosition start){
        if(!LocationService.isValidStart(start)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid region");
        }
        return LocationService.nextPosition(start);
    }

    @PostMapping("/isInRegion")
    public boolean isInRegion(@RequestBody LocationAndRegion locationAndRegion ){
        if(locationAndRegion.getRegion() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid region");
        }
        //this just makes the next if statement a bit easier to read
        Region region =  locationAndRegion.getRegion();
        LngLat location = locationAndRegion.getPosition();

        if (!RegionService.isValid(region) || !LocationService.isValid(location)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid region");
        }

        return RegionService.isInRegion(location, region);

    }

}
