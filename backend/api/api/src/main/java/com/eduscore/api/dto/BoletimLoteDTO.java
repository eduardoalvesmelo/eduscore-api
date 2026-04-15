package com.eduscore.api.dto;

import java.util.List;

public record BoletimLoteDTO(
        List<LancamentoNotaDTO> lancamentos
) {}