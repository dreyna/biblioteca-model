package com.example.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="TBL_LIBROS")
public class Libro {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=0L;
    @Column(name = "TITULO")
    @NotNull
    @NotBlank
    private String titulo;
    @Column(name = "PAGINAS")
    @NotNull @NotBlank
    private Integer paginas;
    @Column(name = "EDICION")
    @NotNull @NotBlank
    private String edicion;
    @Column(name = "ESTADO")
    @NotBlank
    private char estado;

    @ManyToOne
    @JoinColumn(name="SECCION_ID", nullable = false)
    private Seccion seccion;

    @ManyToOne
    @JoinColumn(name="EDITORIAL_ID", nullable = false)
    private Editorial editorial;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libro")
    @JsonIgnore
    private Set<DetallePrestamo> detalles;

    @ManyToMany(
            cascade=CascadeType.ALL
    )
    @JoinTable(
            name = "TBL_LIBROS_AUTORES",
            joinColumns = @JoinColumn(name = "LIBRO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTOR_ID", referencedColumnName = "ID")
    )
    private Set<Autor> autores = new HashSet<>();
}
