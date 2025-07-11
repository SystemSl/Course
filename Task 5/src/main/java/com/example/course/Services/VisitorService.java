package com.example.course.Services;

import com.example.course.Dto.Requests.VisitorRequestDTO;
import com.example.course.Dto.Responses.VisitorResponseDTO;
import com.example.course.Entities.Visitor;
import com.example.course.Mappers.VisitorMapper;
import com.example.course.Repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private VisitorMapper visitorMapper;

    public VisitorResponseDTO save(VisitorRequestDTO dto) {
        Visitor visitor = visitorMapper.toEntity(dto);
        Visitor savedVisitor = visitorRepository.save(visitor);
        return visitorMapper.toResponseDTO(savedVisitor);
    }

    public List<VisitorResponseDTO> findAll() {
        return visitorRepository.findAll().stream()
                .map(visitorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void remove(Long id) {
        visitorRepository.deleteById(id);
    }

    public VisitorResponseDTO update(Long id, VisitorRequestDTO dto) {
        Visitor existingVisitor = visitorRepository.findById(id);
        Visitor updatedVisitor = visitorMapper.toEntity(dto);
        updatedVisitor.setId(existingVisitor.getId());
        Visitor savedVisitor = visitorRepository.save(updatedVisitor);
        return visitorMapper.toResponseDTO(savedVisitor);
    }
}
