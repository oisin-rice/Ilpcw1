package uk.ac.ed.acp.cw2.service;

import uk.ac.ed.acp.cw2.data.LngLat;
import uk.ac.ed.acp.cw2.data.LocationPair;
import uk.ac.ed.acp.cw2.data.Region;

public class RegionService {
    public static boolean isValid(Region region){
        //making things easier to read
        LngLat[] vertices = region.getVertices();

        if(vertices == null){
            return false;
        }
        //checking every vertex in the list given is a valid LngLat
        for (LngLat vertex : vertices) {
            if (!LocationService.isValid(vertex)) {
                return false;
            }
        }
        //making sure the region is closed
        return vertices[0].equals(vertices[vertices.length -1]);
    }

    public static boolean isInRegion(LngLat position, Region region){
        /* Using a ray casting algorithm I found online here,
        ray casting doesn't check the boundaries of the region
        however so needed to check the boundaries also*/

        int count = 0;
        for (int i = 0; i < region.getVertices().length-1; i++){
            //making it easier to read
            LngLat point1 = region.getVertices()[i];
            LngLat point2 = region.getVertices()[i+1];

            //these are used to check the boundaries later
            LocationPair ac = new LocationPair();
            LocationPair cb = new LocationPair();
            LocationPair ab = new LocationPair();

            //initialisation
            ac.setPosition1(point1);
            ac.setPosition2(position);

            cb.setPosition1(position);
            cb.setPosition2(point2);

            ab.setPosition1(point1);
            ab.setPosition2(point2);

            //checking whether the point is on the boundaries of the region
            if((LocationService.calcDistance(ac) + LocationService.calcDistance(cb))
                == LocationService.calcDistance(ab)){
                return true;
            }
            /*
            main part of ray casting algorithm - checks whether a
             ray passes through the region boundaries
             */
            if (position.lat() < point1.lat() != position.lat() < point2.lat() &&
                    (position.lng() < (point1.lng() + ((position.lat() - point1.lat())
                            *(point2.lng()- point1.lng()/ point2.lat()- point1.lat()))))){
                count++;
            }
        }
        /*
        if the ray passes through the region boundaries an odd number of times,
        the point is in the region
         */
        return count%2 == 1;
    }

}
