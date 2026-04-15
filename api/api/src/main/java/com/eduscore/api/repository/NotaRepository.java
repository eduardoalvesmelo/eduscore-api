package com.eduscore.api.repository;

import com.eduscore.api.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    Optional<Nota> findByAlunoIdAndAvaliacaoId(Long alunoId, Long avaliacaoId);
    List<Nota> findByAlunoTurmaId(Long turmaId);
}