package com.example.biblioteca.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeccionDTO{
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "A|I", message = "El estado debe ser 'A' (activo) o 'I' (inactivo)")
    private String estado;

    @NotNull(message = "Debe proporcionar el ID de la categoría")
    private Long categoriaId;
}


