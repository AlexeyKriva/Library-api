package api.modsen.library.entities;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    Book fromBookDtoToBook(BookDto bookDto);
    Book fromBookUpdateInformationDto(BookUpdateDescriptionDto bookUpdateInformationDto);
}
