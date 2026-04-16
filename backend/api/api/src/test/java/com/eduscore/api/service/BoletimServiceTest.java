package com.eduscore.api.service;

import com.eduscore.api.dto.BoletimLoteDTO;
import com.eduscore.api.dto.LancamentoNotaDTO;
import com.eduscore.api.model.Aluno;
import com.eduscore.api.model.Avaliacao;
import com.eduscore.api.model.Nota;
import com.eduscore.api.repository.AlunoRepository;
import com.eduscore.api.repository.AvaliacaoRepository;
import com.eduscore.api.repository.NotaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoletimServiceTest {

    @Mock
    private NotaRepository notaRepository;

    @Mock
    private AlunoRepository alunoRepository; // Ajustado para o padrão do seu repo

    @Mock
    private AvaliacaoRepository avaliacaoRepository; // Ajustado para o padrão do seu repo

    @InjectMocks
    private BoletimService boletimService; // Nome da sua classe de serviço

    @Test
    @DisplayName("Deve salvar notas em lote com sucesso usando BoletimService")
    void deveSalvarNotasEmLote() {
        LancamentoNotaDTO notaDto = new LancamentoNotaDTO(1L, 1L, new BigDecimal("10.0"));
        BoletimLoteDTO loteDto = new BoletimLoteDTO(List.of(notaDto));
        when(notaRepository.findByAlunoIdAndAvaliacaoId(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        when(alunoRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Aluno()));

        when(avaliacaoRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Avaliacao()));

        boletimService.salvarEmLote(loteDto);

        verify(notaRepository, times(1)).save(any(Nota.class));
    }
}