package uk.ac.ed.acp.cw2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.ac.ed.acp.cw2.data.RegionDto;
import uk.ac.ed.acp.cw2.model.Region;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);

    Region regionDtoToRegion(RegionDto regionDto);
    RegionDto regionToRegionDto(Region region);
}
