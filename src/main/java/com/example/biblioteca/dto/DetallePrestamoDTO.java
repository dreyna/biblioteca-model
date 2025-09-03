package com.example.biblioteca.dto;

import java.time.LocalDateTime;

public class DetallePrestamoDTO {
    private Long id;
    private String estado;
    private LocalDateTime fechaEntrega;
    private String observaciones;
    private Long libroId;
    private Long prestamoId;
}
