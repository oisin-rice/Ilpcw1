package uk.ac.ed.acp.cw2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.ac.ed.acp.cw2.data.StartPositionDto;
import uk.ac.ed.acp.cw2.model.StartPosition;

@Mapper(componentModel = "spring")
public interface StartPositionMapper {

    StartPositionMapper INSTANCE = Mappers.getMapper(StartPositionMapper.class);

    StartPosition startPositionDtoToStartPosition(StartPositionDto startPositionDto);
    StartPositionDto startPositionToStartPositionDto(StartPosition startPosition);
}
