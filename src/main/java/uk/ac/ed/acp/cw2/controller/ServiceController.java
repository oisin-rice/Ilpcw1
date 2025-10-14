package uk.ac.ed.acp.cw2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;

import uk.ac.ed.acp.cw2.data.*;
import uk.ac.ed.acp.cw2.mappers.LocationMapper;
import uk.ac.ed.acp.cw2.mappers.LocationPairMapper;
import uk.ac.ed.acp.cw2.mappers.RegionAndLocationMapper;
import uk.ac.ed.acp.cw2.mappers.StartPositionMapper;
import uk.ac.ed.acp.cw2.model.*;
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
        return "s12345678";
    }


    @PostMapping("/distanceTo")
    public double distanceTo(@RequestBody LocationPairDto positions){
        LocationPair locationPair = LocationPairMapper.INSTANCE.locationPairDtoToLocationPair(positions);
        return LocationService.calcDistance(locationPair);
    }

    @PostMapping("/isCloseTo")
    public boolean isCloseTo(@RequestBody LocationPairDto positions){
        LocationPair locationPair = LocationPairMapper.INSTANCE.locationPairDtoToLocationPair(positions);
        return LocationService.calcDistance(locationPair) < 0.00015;
    }

    @PostMapping("/nextPosition")
    public LocationDto nextPosition(@RequestBody StartPositionDto start){
        StartPosition startPosition = StartPositionMapper.INSTANCE.startPositionDtoToStartPosition(start);

        Location finalPosition = LocationService.nextPosition(startPosition);

        return LocationMapper.INSTANCE.locationToLocationDto(finalPosition);
    }

    @PostMapping("/isInRegion")
    public ResponseEntity isInRegion(@RequestBody RegionAndLocationDto inRegion){

        RegionAndLocation regionAndLocation = RegionAndLocationMapper.INSTANCE.raLDtoToRaL(inRegion);

        Region region = regionAndLocation.getRegion();

        boolean isValidRegion = RegionService.isValid(region);

        if (!isValidRegion){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        boolean isInRegion = RegionService.inRegion(regionAndLocation);

        return new ResponseEntity<>(isInRegion,HttpStatus.OK);

    }

}
