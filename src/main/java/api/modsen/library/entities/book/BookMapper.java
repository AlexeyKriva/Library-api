package api.modsen.library.entities.book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "author", target = "author")
    Book fromBookDtoToBook(BookDto bookDto);
    @Mapping(source = "description", target = "description")
    Book fromBookUpdateInformationDto(BookUpdateDescriptionDto bookUpdateInformationDto);
}
