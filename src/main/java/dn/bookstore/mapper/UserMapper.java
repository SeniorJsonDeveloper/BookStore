package dn.bookstore.mapper;

import dn.bookstore.dto.user.UserOutDto;
import dn.bookstore.dto.user.UserRegistryDto;
import dn.bookstore.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {



//    @Mapping(target = "books",source = "books",ignore = true)
//    UserOutDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserRegistryDto userRegistryDto);
}
