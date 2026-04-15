package com.eduscore.api.controller;

import com.eduscore.api.dto.BoletimLoteDTO;
import com.eduscore.api.service.BoletimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotaController {

    private final BoletimService boletimService;

    @PostMapping("/lote")
    public ResponseEntity<Void> salvarLote(@RequestBody @Valid BoletimLoteDTO dto) {
        boletimService.salvarEmLote(dto);
        return ResponseEntity.ok().build();
    }
}