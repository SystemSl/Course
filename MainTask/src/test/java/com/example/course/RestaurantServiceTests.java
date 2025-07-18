package com.example.course;

import com.example.course.Dto.Requests.RestaurantRequestDTO;
import com.example.course.Dto.Responses.RestaurantResponseDTO;
import com.example.course.Entities.Restaurant;
import com.example.course.Mappers.RestaurantMapper;
import com.example.course.Repositories.RestaurantRepository;
import com.example.course.Services.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTests {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void shouldSaveRestaurantSuccessfully() {
        RestaurantRequestDTO dto = new RestaurantRequestDTO("Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L));
        Restaurant restaurant = new Restaurant();
        given(restaurantMapper.toEntity(any())).willReturn(restaurant);
        given(restaurantRepository.save(any())).willReturn(restaurant);
        given(restaurantMapper.toResponseDTO(any())).willReturn(new RestaurantResponseDTO(1L, "Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L), null));

        RestaurantResponseDTO result = restaurantService.save(dto);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindAllRestaurants() {
        Restaurant restaurant1 = new Restaurant();
        Restaurant restaurant2 = new Restaurant();
        given(restaurantRepository.findAll()).willReturn(Arrays.asList(restaurant1, restaurant2));
        given(restaurantMapper.toResponseDTO(any())).willReturn(new RestaurantResponseDTO(1L, "Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L), null));

        List<RestaurantResponseDTO> result = restaurantService.findAll();

        assertThat(result).hasSize(2);
    }

    @Test
    public void shouldRemoveRestaurant() {
        Long id = 1L;
        restaurantService.remove(id);
        verify(restaurantRepository).deleteById(id);
    }

    @Test
    public void shouldUpdateRestaurant() {
        Long id = 1L;
        RestaurantRequestDTO dto = new RestaurantRequestDTO("Updated Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L));
        Restaurant restaurant = new Restaurant();
        given(restaurantRepository.findById(id)).willReturn(Optional.of(restaurant));
        given(restaurantMapper.toEntity(any())).willReturn(restaurant);
        given(restaurantMapper.toResponseDTO(any())).willReturn(new RestaurantResponseDTO(1L, "Updated Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L), null));

        RestaurantResponseDTO result = restaurantService.update(id, dto);

        assertThat(result).isNotNull();
    }
}
