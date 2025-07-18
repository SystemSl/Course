package com.example.course;

import com.example.course.Controllers.VisitorController;
import com.example.course.Dto.Requests.VisitorRequestDTO;
import com.example.course.Dto.Responses.VisitorResponseDTO;
import com.example.course.Services.VisitorService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VisitorController.class)
public class VisitorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VisitorService visitorService;

    @Test
    public void shouldSaveVisitor() throws Exception {
        VisitorRequestDTO dto = new VisitorRequestDTO("Anton", 21, "M");
        VisitorResponseDTO responseDTO = new VisitorResponseDTO(1L, "Anton", 21, "M");
        given(visitorService.save(any())).willReturn(responseDTO);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("""
                                {"name":"Anton","age":21,"gender":"M"}
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Anton")));
    }

    @Test
    public void shouldFindAllVisitors() throws Exception {
        VisitorResponseDTO responseDTO = new VisitorResponseDTO(1L, "Anton", 21, "M");
        given(visitorService.findAll()).willReturn(Arrays.asList(responseDTO));
        mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[")));
    }

    @Test
    public void shouldRemoveVisitor() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/users/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateVisitor() throws Exception {
        Long id = 1L;
        VisitorRequestDTO dto = new VisitorRequestDTO("Updated Anton", 21, "M");
        VisitorResponseDTO responseDTO = new VisitorResponseDTO(1L, "Updated Anton", 21, "M");
        given(visitorService.update(eq(id), any())).willReturn(responseDTO);
        mockMvc.perform(put("/api/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("""
                                {"name":"Updated Anton","age":21,"gender":"M"}
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated")));
    }
}
