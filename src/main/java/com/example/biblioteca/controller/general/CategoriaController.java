package com.example.biblioteca.controller.general;

import com.example.biblioteca.dto.CategoriaDTO;
import com.example.biblioteca.service.general.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("api/v1/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<CategoriaDTO> cats =  categoriaService.findAll();
            if (isNull(cats) || cats.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(cats);
        } catch (Exception e) {
            log.info("Error: " + e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
