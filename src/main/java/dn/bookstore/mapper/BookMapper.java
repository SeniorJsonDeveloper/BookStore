package dn.bookstore.mapper;

import dn.bookstore.dto.google.BookOutDto;
import dn.bookstore.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookEntity toEntity(BookOutDto book);

    BookOutDto toDto(BookEntity book);

    List<BookEntity> toEntityList(List<BookOutDto> books);

    List<BookOutDto> toDtoList(List<BookEntity> books);
}
