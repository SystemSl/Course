package com.example.course.Mappers;

import com.example.course.Dto.Requests.VisitorRequestDTO;
import com.example.course.Dto.Responses.VisitorResponseDTO;
import com.example.course.Entities.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VisitorMapper {

    VisitorMapper INSTANCE = Mappers.getMapper(VisitorMapper.class);

    @Mapping(target = "id", ignore = true)
    Visitor toEntity(VisitorRequestDTO dto);

    VisitorResponseDTO toResponseDTO(Visitor entity);
}
