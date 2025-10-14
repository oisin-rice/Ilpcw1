package uk.ac.ed.acp.cw2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.ac.ed.acp.cw2.data.LocationDto;
import uk.ac.ed.acp.cw2.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    Location locationDtoToLocation(LocationDto locationDto);
    LocationDto locationToLocationDto(Location location);
}
