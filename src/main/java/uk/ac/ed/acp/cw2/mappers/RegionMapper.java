package uk.ac.ed.acp.cw2.mappers;

import org.mapstruct.Mapper;
import uk.ac.ed.acp.cw2.data.RegionDto;
import uk.ac.ed.acp.cw2.model.Region;

@Mapper
public interface RegionMapper {
    Region regionDtoToRegion(RegionDto regionDto);
    RegionDto regionToRegionDto(Region region);
}
