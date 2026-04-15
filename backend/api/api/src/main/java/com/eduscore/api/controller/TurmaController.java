package com.eduscore.api.controller;

import com.eduscore.api.model.Turma;
import com.eduscore.api.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @GetMapping
    public List<Turma> listar() {
        return repository.findAll();
    }
}