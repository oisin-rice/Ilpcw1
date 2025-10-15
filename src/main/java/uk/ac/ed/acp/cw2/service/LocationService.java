package uk.ac.ed.acp.cw2.service;

import uk.ac.ed.acp.cw2.data.Location;
import uk.ac.ed.acp.cw2.data.LocationPair;
import uk.ac.ed.acp.cw2.data.StartPosition;

import static java.lang.Math.*;

public class LocationService {

    public static boolean isValid(Location location){
        if (location == null){
            return false;
        }
        else return location.lng() != null && location.lat() != null;
    }

    public static boolean isValidPair(LocationPair locationPair){
        return (isValid(locationPair.getPosition1()) & isValid(locationPair.getPosition2()));
    }

    public static boolean isValidStart(StartPosition startPosition){
        if(!isValid(startPosition.start())){
            return false;
        }
        if(startPosition.angle() == null){
            return false;
        }
        else return startPosition.angle() % 22.5 == 0;
    }
    public static double calcDistance(LocationPair locationPair){

        Location position1 = locationPair.getPosition1();
        Location position2 = locationPair.getPosition2();

        double lngDist = abs(position2.lng() - position1.lng());
        double latDist = abs(position2.lat() - position1.lat());

        return sqrt(lngDist*lngDist + latDist*latDist);
    }

    public static Location nextPosition(StartPosition startPosition){
        double angle = startPosition.angle();

        double nextX = startPosition.start().lng() + 0.00015 * cos(angle);
        double nextY = startPosition.start().lat() + 0.00015 * sin(angle);

        return new Location(nextX, nextY);
    }
}
