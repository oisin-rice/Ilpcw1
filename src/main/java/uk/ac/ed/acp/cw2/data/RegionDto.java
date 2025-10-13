package uk.ac.ed.acp.cw2.data;

import lombok.Data;

@Data
public class RegionDto {
    private String name;
    private LocationDto[] vertices;
}
