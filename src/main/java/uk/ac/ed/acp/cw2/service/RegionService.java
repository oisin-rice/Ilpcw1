package uk.ac.ed.acp.cw2.service;

import uk.ac.ed.acp.cw2.data.Location;
import uk.ac.ed.acp.cw2.data.Region;

public class RegionService {
    public static boolean isValid(Region region){
        Location[] vertices = region.getVertices();
        return vertices[0].equals(vertices[vertices.length -1]);
    }

    public static boolean inRegion(Location position, Region region){
        int count = 0;
        for (int i = 0; i < region.getVertices().length-2; i++){
            Location point1 = region.getVertices()[i];
            Location point2 = region.getVertices()[i+1];

            if (position.lat() < point1.lat() != position.lat() < point2.lat() &&
                    (position.lng() < (point1.lng() + ((position.lat() - point1.lat())
                            *(point2.lng()- point1.lng()/ point2.lat()- point1.lat()))))){
                count++;
            }
        }
        return count%2 == 1;
    }

}
