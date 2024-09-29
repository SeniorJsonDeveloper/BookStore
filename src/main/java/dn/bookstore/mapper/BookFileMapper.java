package dn.bookstore.mapper;

import dn.bookstore.dto.google.BookFileDto;
import dn.bookstore.entity.BookFileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookFileMapper {

    BookFileEntity toEntity(BookFileDto bookFileDto);

    @Mapping(source = "book.title",target = "book.title")
    BookFileDto toDto(BookFileEntity bookFileEntity);

    List<BookFileEntity> toEntityList(List<BookFileDto> bookFileDtos);

    List<BookFileDto> toDtoList(List<BookFileEntity> bookFileEntityList);
}
