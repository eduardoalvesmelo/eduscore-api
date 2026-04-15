package com.eduscore.api.controller;

import com.eduscore.api.model.Avaliacao;
import com.eduscore.api.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository repository;

    @GetMapping
    public List<Avaliacao> listar(@RequestParam Long disciplinaId) {
        return repository.findByDisciplinaId(disciplinaId);
    }
}