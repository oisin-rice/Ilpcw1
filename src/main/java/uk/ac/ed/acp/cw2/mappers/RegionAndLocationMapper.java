package uk.ac.ed.acp.cw2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.ac.ed.acp.cw2.data.RegionAndLocationDto;
import uk.ac.ed.acp.cw2.model.RegionAndLocation;
@Mapper(componentModel = "spring")
public interface RegionAndLocationMapper {

    RegionAndLocationMapper INSTANCE = Mappers.getMapper(RegionAndLocationMapper.class);
    RegionAndLocation raLDtoToRaL(RegionAndLocationDto raLDto);
    RegionAndLocationDto raLToRaLDto(RegionAndLocation raL);
}
