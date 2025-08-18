package com.example.biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="TBL_AUTORES")
public class Autor {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id=0L;
    @Column(name = "NOMBRES")
    @NotNull
    @NotBlank
    private String nombres;
    @Column(name = "APELLIDOS")
    @NotNull @NotBlank
    private String apellidos;
    @Column(name = "PAIS")
    @NotNull @NotBlank
    private String pais;
    @Column(name = "ESTADO")
    @NotNull
    private char estado;
}
