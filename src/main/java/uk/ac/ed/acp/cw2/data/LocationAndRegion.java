package uk.ac.ed.acp.cw2.data;

import lombok.Data;

@Data
public class LocationAndRegion {
    private LngLat position;
    private Region region;
}
