package com.eduscore.api.controller;

import com.eduscore.api.model.Aluno;
import com.eduscore.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> listar(@RequestParam Long turmaId) {
        return repository.findByTurmaId(turmaId);
    }
}