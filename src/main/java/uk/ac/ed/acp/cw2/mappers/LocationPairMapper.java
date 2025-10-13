package uk.ac.ed.acp.cw2.mappers;

import org.mapstruct.Mapper;
import uk.ac.ed.acp.cw2.data.LocationPairDto;
import uk.ac.ed.acp.cw2.model.LocationPair;

@Mapper
public interface LocationPairMapper {
    LocationPair locationPairDtoToLocationPair(LocationPairDto locationPairDto);
    LocationPairDto locationToLocationPairDto(LocationPair locationPair);
}
