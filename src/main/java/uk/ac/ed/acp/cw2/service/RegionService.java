package uk.ac.ed.acp.cw2.service;

import uk.ac.ed.acp.cw2.model.Location;
import uk.ac.ed.acp.cw2.model.Region;
import uk.ac.ed.acp.cw2.model.RegionAndLocation;

public class RegionService {
    public static boolean isValid(Region region){
        Location[] vertices = region.getVertices();
        return vertices[0].equals(vertices[vertices.length -1]);
    }
    public static boolean inRegion(RegionAndLocation regionAndLocation){
        int count = 0;
        for (int i = 0; i < regionAndLocation.getRegion().getVertices().length-2; i++){
            Location point1 = regionAndLocation.getRegion().getVertices()[i];
            Location point2 = regionAndLocation.getRegion().getVertices()[i+1];

            Location position = regionAndLocation.getPosition();

            if (position.getLat() < point1.getLat() != position.getLat() < point2.getLat() &&
                    (position.getLng() < (point1.getLng() + ((position.getLat() - point1.getLat())
                            *(point2.getLng()- point1.getLng()/ point2.getLat()- point1.getLat()))))){
                count++;
            }
        }
        return count%2 == 1;
    }
}
