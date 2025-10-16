package uk.ac.ed.acp.cw2.data;


import lombok.Data;

import static java.lang.Math.abs;

@Data
public class LocationPair {

    private LngLat position1;
    private LngLat position2;

}
