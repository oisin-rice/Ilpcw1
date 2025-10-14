package uk.ac.ed.acp.cw2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.ac.ed.acp.cw2.data.LocationPairDto;
import uk.ac.ed.acp.cw2.model.LocationPair;

@Mapper(componentModel = "spring")
public interface LocationPairMapper {

    LocationPairMapper INSTANCE = Mappers.getMapper(LocationPairMapper.class);

    LocationPair locationPairDtoToLocationPair(LocationPairDto locationPairDto);
    LocationPairDto locationToLocationPairDto(LocationPair locationPair);
}
