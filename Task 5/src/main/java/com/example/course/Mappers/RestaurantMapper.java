package com.example.course.Mappers;

import com.example.course.Dto.Requests.RestaurantRequestDTO;
import com.example.course.Dto.Responses.RestaurantResponseDTO;
import com.example.course.Entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "id", ignore = true)
    Restaurant toEntity(RestaurantRequestDTO dto);

    RestaurantResponseDTO toResponseDTO(Restaurant entity);
}
