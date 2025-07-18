package com.example.course;

import com.example.course.Dto.Requests.VisitorRequestDTO;
import com.example.course.Dto.Responses.VisitorResponseDTO;
import com.example.course.Entities.Visitor;
import com.example.course.Mappers.VisitorMapper;
import com.example.course.Repositories.VisitorRepository;
import com.example.course.Services.VisitorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VisitorServiceTests {

    @Mock
    private VisitorRepository visitorRepository;

    @Mock
    private VisitorMapper visitorMapper;

    @InjectMocks
    private VisitorService visitorService;

    @Test
    public void shouldSaveVisitorSuccessfully() {
        VisitorRequestDTO dto = new VisitorRequestDTO("Anton", 21, "M");
        Visitor visitor = new Visitor();
        given(visitorMapper.toEntity(any())).willReturn(visitor);
        given(visitorRepository.save(any())).willReturn(visitor);
        given(visitorMapper.toResponseDTO(any())).willReturn(new VisitorResponseDTO(1L, "Anton", 21, "M"));

        VisitorResponseDTO result = visitorService.save(dto);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindAllVisitors() {
        Visitor visitor1 = new Visitor();
        Visitor visitor2 = new Visitor();
        given(visitorRepository.findAll()).willReturn(Arrays.asList(visitor1, visitor2));
        given(visitorMapper.toResponseDTO(any())).willReturn(new VisitorResponseDTO(1L, "Anton", 21, "M"));

        List<VisitorResponseDTO> result = visitorService.findAll();
        assertThat(result).hasSize(2);
    }

    @Test
    public void shouldRemoveVisitor() {
        Long id = 1L;
        visitorService.remove(id);
        verify(visitorRepository).deleteById(id);
    }

    @Test
    public void shouldUpdateVisitor() {
        Long id = 1L;
        VisitorRequestDTO dto = new VisitorRequestDTO("Updated anton", 31, "Male");
        Visitor visitor = new Visitor();
        given(visitorRepository.findById(id)).willReturn(Optional.of(visitor));
        given(visitorMapper.toEntity(any())).willReturn(visitor);
        given(visitorMapper.toResponseDTO(any())).willReturn(new VisitorResponseDTO(1L, "Updated John", 31, "Male"));

        VisitorResponseDTO result = visitorService.update(id, dto);

        assertThat(result).isNotNull();
    }
}