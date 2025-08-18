package com.example.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO{
    private Long id;

    @NotBlank(message = "El nombre no debe estar vacío")
    private String nombre;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^[AI]$", message = "El estado debe ser 'A' o 'I'")
    private String estado;
}

