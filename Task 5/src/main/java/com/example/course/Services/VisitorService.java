package com.example.course.Services;

import com.example.course.Entities.Visitor;
import com.example.course.Repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository repository;

    public void save(Visitor visitor) {
        repository.save(visitor);
    }

    public void remove(Visitor visitor) {
        repository.remove(visitor);
    }

    public List<Visitor> findAll() {
        return repository.findAll();
    }
}
