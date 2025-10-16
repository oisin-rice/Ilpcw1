package uk.ac.ed.acp.cw2.service;

import uk.ac.ed.acp.cw2.data.Location;
import uk.ac.ed.acp.cw2.data.LocationPair;
import uk.ac.ed.acp.cw2.data.Region;

public class RegionService {
    public static boolean isValid(Region region){
        Location[] vertices = region.getVertices();

        if(vertices == null){
            return false;
        }
        for (Location vertex : vertices) {
            if (!LocationService.isValid(vertex)) {
                return false;
            }
        }
        return vertices[0].equals(vertices[vertices.length -1]);
    }

    public static boolean isInRegion(Location position, Region region){
        int count = 0;
        for (int i = 0; i < region.getVertices().length-1; i++){
            Location point1 = region.getVertices()[i];
            Location point2 = region.getVertices()[i+1];

            LocationPair ac = new LocationPair();
            LocationPair cb = new LocationPair();
            LocationPair ab = new LocationPair();

            ac.setPosition1(point1);
            ac.setPosition2(position);

            cb.setPosition1(position);
            cb.setPosition2(point2);

            ab.setPosition1(point1);
            ab.setPosition2(point2);

            if((LocationService.calcDistance(ac) + LocationService.calcDistance(cb))
                == LocationService.calcDistance(ab)){
                return true;
            }
            if (position.lat() < point1.lat() != position.lat() < point2.lat() &&
                    (position.lng() < (point1.lng() + ((position.lat() - point1.lat())
                            *(point2.lng()- point1.lng()/ point2.lat()- point1.lat()))))){
                count++;
            }
        }
        return count%2 == 1;
    }

}
