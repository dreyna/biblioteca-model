package com.example.biblioteca.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class PrestamoDTO {
    private Long id;
    private LocalDate fecha_entrega;
    private LocalDateTime fecha_prestamo;
    private Long lectorId;
    private Long usuarioId;
    private String estado;
    private Set<DetallePrestamoDTO> detalles;
}
