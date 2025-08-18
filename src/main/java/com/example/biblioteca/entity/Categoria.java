package com.example.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="TBL_CATEGORIAS")
public class Categoria {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=0L;
    @Column(name = "NOMBRE")
    @NotNull
    @NotBlank
    private String nombre;
    @Column(name = "ESTADO")
    @NotBlank
    private char estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoria")
    @JsonIgnore
    private Set<Seccion> secciones;
}
