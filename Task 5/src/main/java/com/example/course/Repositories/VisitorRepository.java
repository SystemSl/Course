package com.example.course.Repositories;

import com.example.course.Entities.Visitor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VisitorRepository {

    private Long idCounter = 1L;

    private final List<Visitor> visitors = new ArrayList<>();

    public Visitor save(Visitor visitor) {
        visitor.setId(idCounter);
        idCounter++;
        visitors.add(visitor);
        return visitor;
    }

    public List<Visitor> findAll() {
        return visitors;
    }

    public void deleteById(Long id) {
        visitors.removeIf(visitor -> visitor.getId().equals(id));
    }

    public Visitor findById(Long id) {
        return visitors.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}