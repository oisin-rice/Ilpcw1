package uk.ac.ed.acp.cw2.service;

import uk.ac.ed.acp.cw2.model.Location;
import uk.ac.ed.acp.cw2.model.LocationPair;
import uk.ac.ed.acp.cw2.model.StartPosition;

import static java.lang.Math.*;
import static java.lang.Math.sin;

public class LocationService {
    public static double calcDistance(LocationPair locationPair){

        Location position1 = locationPair.getPosition1();
        Location position2 = locationPair.getPosition2();

        double lngDist = abs(position2.getLng() - position1.getLng());
        double latDist = abs(position2.getLat() - position1.getLat());

        return sqrt(lngDist*lngDist + latDist*latDist);
    }

    public static Location nextPosition(StartPosition startPosition){
        double angle = startPosition.getAngle();

        double nextX = startPosition.getStartPosition().getLng() + 0.00015 * cos(angle);
        double nextY = startPosition.getStartPosition().getLat() + 0.00015 * sin(angle);

        Location nextPosition = new Location();
        nextPosition.setLat(nextX);
        nextPosition.setLng(nextY);

        return nextPosition;
    }
}
