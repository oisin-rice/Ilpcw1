package uk.ac.ed.acp.cw2.data;


import lombok.Data;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

@Data
public class LocationPairDto {

    private LocationDto position1;
    private LocationDto position2;

    public double calcDistance(){
        double lngDist = abs(position2.lng() - position1.lng());
        double latDist = abs(position2.lat() - position1.lat());

        return sqrt(lngDist*lngDist + latDist*latDist);
    }
}
