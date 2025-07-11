package com.example.course.Controllers;

import com.example.course.Dto.Requests.VisitorRequestDTO;
import com.example.course.Dto.Responses.VisitorResponseDTO;
import com.example.course.Services.VisitorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public ResponseEntity<VisitorResponseDTO> saveVisitor(@Validated @RequestBody VisitorRequestDTO dto) {
        VisitorResponseDTO createdVisitor = visitorService.save(dto);
        return new ResponseEntity<>(createdVisitor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VisitorResponseDTO>> findAllVisitors() {
        List<VisitorResponseDTO> visitors = visitorService.findAll();
        return ResponseEntity.ok(visitors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeVisitor(@PathVariable Long id) {
        visitorService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitorResponseDTO> updateVisitor(@PathVariable Long id, @Valid @RequestBody VisitorRequestDTO dto) {
        VisitorResponseDTO updatedVisitor = visitorService.update(id, dto);
        return ResponseEntity.ok(updatedVisitor);
    }
}
