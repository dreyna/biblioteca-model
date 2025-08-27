package com.example.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO{
    private Long id;

    @NotBlank(message = "{NotBlank.categoriaDTO.nombre}")
    @Size(max=40, message = "{Size.categoriaDTO.nombre}")
    private String nombre;

    @NotBlank(message = "{NotNull.categoriaDTO.estado}")
    @Pattern(regexp ="A|I", message="{Pattern.categoriaDTO.estado}")
    private String estado;
}

