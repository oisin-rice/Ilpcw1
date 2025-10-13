package uk.ac.ed.acp.cw2.model;

import lombok.Getter;
import lombok.Setter;
import uk.ac.ed.acp.cw2.data.LocationDto;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

@Getter
@Setter
public class LocationPair {
    private Location position1;
    private Location position2;
}
