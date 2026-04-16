package com.eduscore.api.controller;

import com.eduscore.api.ApiApplication;
import com.eduscore.api.dto.BoletimLoteDTO;
import com.eduscore.api.dto.LancamentoNotaDTO;
import com.eduscore.api.service.BoletimService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
class NotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BoletimService boletimService;

    @Test
    @DisplayName("Deve retornar HTTP 200 ao enviar um lote de notas válido")
    void deveRetornarOkParaLoteValido() throws Exception {
        LancamentoNotaDTO nota = new LancamentoNotaDTO(1L, 1L, new BigDecimal("9.5"));
        BoletimLoteDTO lote = new BoletimLoteDTO(List.of(nota));
        mockMvc.perform(post("/api/notas/lote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lote)))
                .andExpect(status().isOk());
    }
}