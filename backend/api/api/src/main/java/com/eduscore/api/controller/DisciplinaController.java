package com.eduscore.api.controller;

import com.eduscore.api.model.Disciplina;
import com.eduscore.api.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @GetMapping
    public List<Disciplina> listar() {
        return repository.findAll();
    }
}