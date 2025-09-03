package com.example.biblioteca.controller.general;

import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.dto.PrestamoRequestDTO;
import com.example.biblioteca.service.general.service.PrestamoService;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {
    private final PrestamoService pService;

    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> readAll(){
        return ResponseEntity.ok(pService.readAll());
    }
    @PostMapping
    public ResponseEntity<PrestamoDTO> save(@RequestBody PrestamoRequestDTO dto) throws ServiceException {
        return ResponseEntity.status(HttpStatus.CREATED).body(pService.save(dto));
    }
}
