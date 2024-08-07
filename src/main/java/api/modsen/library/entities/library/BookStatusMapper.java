package api.modsen.library.entities.library;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookStatusMapper {
    BookStatusMapper INSTANCE = Mappers.getMapper(BookStatusMapper.class);
    @Mapping(source = "borrowedAt", target = "borrowedAt")
    @Mapping(source = "returnAt", target = "returnAt")
    BookStatus fromBookStatusDtoToBookStatus(BookStatusDto bookStatusDto);
    @Mapping(source = "borrowedAt", target = "borrowedAt")
    BookStatus fromBookStatusBorrowedTimeDto(BookStatusBorrowedTimeDto bookStatusBorrowedTimeDto);
}
