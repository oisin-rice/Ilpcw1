package uk.ac.ed.acp.cw2.service;

import uk.ac.ed.acp.cw2.data.LngLat;
import uk.ac.ed.acp.cw2.data.LocationPair;
import uk.ac.ed.acp.cw2.data.StartPosition;

import static java.lang.Math.*;

public class LocationService {

    public static boolean isValid(LngLat location){
        if (location == null){
            return false;
        }
        //checking if the longitude or latitude is null
        else return location.lng() != null && location.lat() != null;
    }

    public static boolean isValidPair(LocationPair locationPair){
        //checking if both locations in a pair ar valid
        return (isValid(locationPair.getPosition1()) & isValid(locationPair.getPosition2()));
    }

    public static boolean isValidStart(StartPosition startPosition){
        //check if the start location is valid LngLat
        if(!isValid(startPosition.start())){
            return false;
        }
        if(startPosition.angle() == null){
            return false;
        }
        //making sure the angle is one of the 16 possible valid directions
        else return startPosition.angle() % 22.5 == 0;
    }
    public static double calcDistance(LocationPair locationPair){

        //making things a bit easier to read
        LngLat position1 = locationPair.getPosition1();
        LngLat position2 = locationPair.getPosition2();

        //the first bit of Euclidean distance calculation
        double lngDist = abs(position2.lng() - position1.lng());
        double latDist = abs(position2.lat() - position1.lat());

        //returning the Euclidean distance
        return sqrt(lngDist*lngDist + latDist*latDist);
    }

    public static LngLat nextPosition(StartPosition startPosition){
        double angle = startPosition.angle();

        //moving 0.00015 in the desired direction
        double nextX = startPosition.start().lng() + 0.00015 * cos(angle);
        double nextY = startPosition.start().lat() + 0.00015 * sin(angle);

        return new LngLat(nextX, nextY);
    }
}
