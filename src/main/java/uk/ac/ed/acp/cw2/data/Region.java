package uk.ac.ed.acp.cw2.data;

import lombok.Data;

@Data
public class Region {
    private String name;
    private Location[] vertices;

    public boolean isValid(){
        for(int i = 1; i < vertices.length; i++){
            if (vertices[0].equals(vertices[i])){
                return true;
            }
        }
        return false;
    }
}
