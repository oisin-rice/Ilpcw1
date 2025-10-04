package uk.ac.ed.acp.cw2.data;

import lombok.Data;

@Data
public class Region {
    private String name;
    private Location[] vertices;

    public boolean isValid(){
        return vertices[0].equals(vertices[vertices.length -1]);
    }
}
