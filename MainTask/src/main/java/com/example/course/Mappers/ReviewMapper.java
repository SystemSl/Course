package com.example.course.Mappers;

import com.example.course.Dto.Requests.ReviewRequestDTO;
import com.example.course.Dto.Responses.ReviewResponseDTO;
import com.example.course.Entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "dto.visitorId", target = "id.visitorId")
    @Mapping(source = "dto.restaurantId", target = "id.restaurantId")
    Review toEntity(ReviewRequestDTO dto);

    @Mapping(source = "entity.id.visitorId", target = "visitorId")
    @Mapping(source = "entity.id.restaurantId", target = "restaurantId")
    ReviewResponseDTO toResponseDTO(Review entity);
}