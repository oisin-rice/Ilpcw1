package uk.ac.ed.acp.cw2.model;

import lombok.Getter;
import lombok.Setter;
import uk.ac.ed.acp.cw2.data.LocationDto;
import uk.ac.ed.acp.cw2.data.RegionDto;

@Getter
@Setter
public class RegionAndLocation {
    private Location position;
    private Region region;
}
