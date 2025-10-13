package uk.ac.ed.acp.cw2.data;

import lombok.Data;

@Data
public class RegionAndLocationDto {
    private LocationDto position;
    private RegionDto region;
}
