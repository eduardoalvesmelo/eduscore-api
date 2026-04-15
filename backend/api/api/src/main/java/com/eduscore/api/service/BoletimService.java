package com.eduscore.api.service;

import com.eduscore.api.dto.BoletimLoteDTO;
import com.eduscore.api.dto.LancamentoNotaDTO;
import com.eduscore.api.model.*;
import com.eduscore.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class BoletimService {

    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    public BoletimService(NotaRepository notaRepository, AlunoRepository alunoRepository, AvaliacaoRepository avaliacaoRepository) {
        this.notaRepository = notaRepository;
        this.alunoRepository = alunoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Transactional
    public void salvarEmLote(BoletimLoteDTO dto) {
        for (LancamentoNotaDTO lancamento : dto.lancamentos()) {
            Nota nota = notaRepository.findByAlunoIdAndAvaliacaoId(lancamento.alunoId(), lancamento.avaliacaoId())
                    .orElse(new Nota());

            if (nota.getId() == null) {
                if (nota.getId() == null) {
                    nota.setAluno(alunoRepository.findById(lancamento.alunoId())
                            .orElseThrow(() -> new RuntimeException("Aluno não encontrado")));

                    nota.setAvaliacao(avaliacaoRepository.findById(lancamento.avaliacaoId())
                            .orElseThrow(() -> new RuntimeException("Avaliação não encontrada")));
                }
            }

            nota.setValor(lancamento.valor());
            notaRepository.save(nota);
        }
    }

    public String calcularMediaPonderada(List<Nota> notas, List<Avaliacao> avaliacoes) {
        if (notas.isEmpty() || notas.size() < avaliacoes.size()) {
            return "-";
        }

        BigDecimal somaProdutos = BigDecimal.ZERO;
        int somaPesos = 0;

        for (Nota nota : notas) {
            BigDecimal peso = new BigDecimal(nota.getAvaliacao().getPeso());
            somaProdutos = somaProdutos.add(nota.getValor().multiply(peso));
            somaPesos += nota.getAvaliacao().getPeso();
        }

        if (somaPesos == 0) return "-";

        BigDecimal media = somaProdutos.divide(new BigDecimal(somaPesos), 1, RoundingMode.HALF_UP);
        return media.toString();
    }
}