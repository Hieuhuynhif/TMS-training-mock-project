package com.example.tmstraining.dtos.user;

import com.example.tmstraining.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDTO> toDTOList(List<User> users);

    UserDTO toDTO(User user);

}
