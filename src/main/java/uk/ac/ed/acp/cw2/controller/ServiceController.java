package uk.ac.ed.acp.cw2.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.time.Instant;

import uk.ac.ed.acp.cw2.data.*;
import uk.ac.ed.acp.cw2.mappers.LocationPairMapper;
import uk.ac.ed.acp.cw2.model.LocationPair;
import uk.ac.ed.acp.cw2.service.LocationService;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

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
        return "s12345678";
    }


    @PostMapping("/distanceTo")
    public double distanceTo(@RequestBody LocationPairDto positions){
        LocationPair locationPair = LocationPairMapper.locationPairDtoToLocationPair(positions);
        return LocationService.calcDistance(locationPair);
    }

    @PostMapping("/isCloseTo")
    public boolean isCloseTo(@RequestBody LocationPairDto positions){
        return positions.calcDistance() < 0.00015;
    }

    @PostMapping("/nextPosition")
    public LocationDto nextPosition(@RequestBody StartPosition start){
        float angle = start.angle();

        double nextX = start.start().lng() + 0.00015 * cos(angle);
        double nextY = start.start().lat() + 0.00015 * sin(angle);

        return new LocationDto(nextX, nextY);
    }

    @PostMapping("/isInRegion")
    public ResponseEntity isInRegion(@RequestBody RegionAndLocationDto inRegion){
        if (!inRegion.getRegion().isValid()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean isIn = inRegion.inRegion();
        return new ResponseEntity<>(isIn,HttpStatus.OK);

    }

}
