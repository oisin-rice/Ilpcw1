package uk.ac.ed.acp.cw2.data;


import lombok.Data;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

@Data
public class LocationPair {

    private Location position1;
    private Location position2;

    public double calcDistance(){
        double lngDist = abs(position2.getLng() - position1.getLng());
        double latDist = abs(position2.getLat() - position1.getLat());

        return sqrt(lngDist*lngDist + latDist*latDist);
    }
}
