package api.modsen.library.bookmicroservice.entities.book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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
    @Mapping(source = "isbn", target = "isbn")
    Book fromBookUpdateIsbnDtoToBook(BookUpdateIsbnDto bookUpdateIsbnDto);
    @Mapping(source = "title", target = "title")
    Book fromBookUpdateTitleDtoToBook(BookUpdateTitleDto bookUpdateTitleDto);
    @Mapping(source = "genre", target = "genre")
    Book fromBookUpdateGenreDtoToBook(BookUpdateGenreDto bookUpdateGenreDto);
    @Mapping(source = "description", target = "description")
    Book fromBookUpdateDescriptionDtoToBook(BookUpdateDescriptionDto bookUpdateInformationDto);
    @Mapping(source = "author", target = "author")
    Book fromBookUpdateAuthorDtoToBook(BookUpdateAuthorDto bookUpdateAuthorDto);

    void updateBookFromBookDto(BookDto bookDto, @MappingTarget Book book);
}
