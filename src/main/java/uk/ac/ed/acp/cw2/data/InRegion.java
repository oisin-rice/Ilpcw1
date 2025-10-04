package uk.ac.ed.acp.cw2.data;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;

@Data
public class InRegion {
    private Location position;
    private Region region;

    //using ray casting algorithm I found online.. super hard to read right now might break it up a bit
    public boolean inRegion(){
       int count = 0;
       for (int i = 0; i < region.getVertices().length-2; i++){
           Location point1 = region.getVertices()[i];
           Location point2 = region.getVertices()[i+1];

           if (position.lat() < point1.lat() != position.lat() < point2.lat() &&
           (position.lng() < (point1.lng() + ((position.lat() - point1.lat())
                   *(point2.lng()- point1.lng()/ point2.lat()- point1.lat()))))){
               count++;
           }
       }
       return count%2 == 1;
    }
}
