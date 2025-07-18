package com.example.course;

import com.example.course.Config.ReviewPK;
import com.example.course.Controllers.ReviewController;
import com.example.course.Dto.Requests.ReviewRequestDTO;
import com.example.course.Dto.Responses.ReviewResponseDTO;
import com.example.course.Services.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReviewController.class)
public class ReviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReviewService reviewService;

    @Test
    public void shouldSaveReview() throws Exception {
        ReviewRequestDTO dto = new ReviewRequestDTO(1L, 1L, 5, "Great place!");
        ReviewResponseDTO responseDTO = new ReviewResponseDTO(1L, 1L, 5, "Great place!");
        given(reviewService.save(any())).willReturn(responseDTO);

        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("""
                                {"visitorId":1,"restaurantId":1,"rating":5,"comment":"Great place!"}
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Great")));
    }

    @Test
    public void shouldFindAllReviews() throws Exception {
        ReviewResponseDTO responseDTO = new ReviewResponseDTO(1L, 1L, 5, "Great place!");
        given(reviewService.findAll()).willReturn(Arrays.asList(responseDTO));

        mockMvc.perform(get("/api/reviews"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")));
    }

    @Test
    public void shouldRemoveReview() throws Exception {
        ReviewPK id = new ReviewPK();
        id.setVisitorId(1L);
        id.setRestaurantId(1L);

        mockMvc.perform(delete("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("""
                                {"visitorId":1,"restaurantId":1}
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateReview() throws Exception {
        ReviewPK id = new ReviewPK();
        id.setVisitorId(1L);
        id.setRestaurantId(1L);
        ReviewRequestDTO dto = new ReviewRequestDTO(1L, 1L, 4, "Still great but needs improvement");
        ReviewResponseDTO responseDTO = new ReviewResponseDTO(1L, 1L, 5, "Still great but needs improvement!");
        given(reviewService.update(any())).willReturn(responseDTO);

        mockMvc.perform(put("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("""
                                {"visitorId":1,"restaurantId":1,"rating":4,"comment":"Still great but needs improvement"}
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("improvement")));
    }
}
