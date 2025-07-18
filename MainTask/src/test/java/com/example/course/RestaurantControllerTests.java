package com.example.course;

import com.example.course.Controllers.RestaurantController;
import com.example.course.Dto.Requests.RestaurantRequestDTO;
import com.example.course.Dto.Responses.RestaurantResponseDTO;
import com.example.course.Entities.Restaurant;
import com.example.course.Services.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestaurantService restaurantService;

    @Test
    public void shouldSaveRestaurant() throws Exception {
        RestaurantRequestDTO dto = new RestaurantRequestDTO("Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L));
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO(1L, "Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L), null);
        given(restaurantService.save(any())).willReturn(responseDTO);

        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("""
                                {"name":"Pizza Hut","description":"Best pizza place","kitchenType":"ITALIAN","averageBill":1500}
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Pizza Hut")));
    }

    @Test
    public void shouldFindAllRestaurants() throws Exception {
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO(1L, "Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L), null);
        given(restaurantService.findAll()).willReturn(Arrays.asList(responseDTO));

        mockMvc.perform(get("/api/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")));
    }

    @Test
    public void shouldRemoveRestaurant() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/restaurants/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateRestaurant() throws Exception {
        Long id = 1L;
        RestaurantRequestDTO dto = new RestaurantRequestDTO("Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L));
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO(1L, "Updated Pizza Hut", "Best pizza place", Restaurant.KitchenType.ITALIAN, BigDecimal.valueOf(1500L), null);
        given(restaurantService.update(eq(id), any())).willReturn(responseDTO); // используем eq() для первого аргумента

        mockMvc.perform(put("/api/restaurants/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("""
                                {"name":"Updated Pizza Hut","description":"Awesome pizza place","kitchenType":"ITALIAN","averageBill":1600}
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated")));
    }
}
