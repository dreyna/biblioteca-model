package com.example.biblioteca.dto;

import java.util.List;

public class PrestamoRequestDTO {
    private Long lectorId;
    private Long usuarioId;
    private List<DetallePrestamoDTO> detalles;
}
