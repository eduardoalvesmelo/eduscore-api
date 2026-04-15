package com.eduscore.api.dto;

import java.math.BigDecimal;

public record LancamentoNotaDTO(
        Long alunoId,
        Long avaliacaoId,
        BigDecimal valor
) {}