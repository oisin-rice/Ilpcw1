package uk.ac.ed.acp.cw2.mappers;

import org.mapstruct.Mapper;
import uk.ac.ed.acp.cw2.data.LocationDto;
import uk.ac.ed.acp.cw2.model.Location;

@Mapper
public interface LocationMapper {
    Location locationDtoToLocation(LocationDto locationDto);
    LocationDto locationToLocationDto(Location location);
}
