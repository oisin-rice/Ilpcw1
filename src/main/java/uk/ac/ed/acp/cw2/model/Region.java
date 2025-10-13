package uk.ac.ed.acp.cw2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Region {
    private String name;
    private Location[] vertices;

    public boolean isValid(){
        return vertices[0].equals(vertices[vertices.length -1]);
    }
}
