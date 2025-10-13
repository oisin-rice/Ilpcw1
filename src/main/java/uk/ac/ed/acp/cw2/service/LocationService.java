package uk.ac.ed.acp.cw2.service;

import uk.ac.ed.acp.cw2.model.Location;
import uk.ac.ed.acp.cw2.model.LocationPair;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class LocationService {
    public static double calcDistance(LocationPair locationPair){

        Location position1 = locationPair.getPosition1();
        Location position2 = locationPair.getPosition2();

        double lngDist = abs(position2.getLng() - position1.getLng());
        double latDist = abs(position2.getLat() - position1.getLat());

        return sqrt(lngDist*lngDist + latDist*latDist);
    }
}
