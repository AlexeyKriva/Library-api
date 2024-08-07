package api.modsen.library.entities.library;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookStatusMapper {
    BookStatusMapper INSTANCE = Mappers.getMapper(BookStatusMapper.class);
    BookStatus fromBookStatusDtoToBookStatus(BookStatusDto bookStatusDto);
}
