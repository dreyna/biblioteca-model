package com.example.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="TBL_LECTORES")
public class Lector {
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
    @Column(name = "DOCUMENTO")
    @NotNull @NotBlank
    private Integer documento;
    @Column(name = "TELEFONO")
    @NotNull @NotBlank
    private Integer telefono;
    @Column(name = "DIRECCION")
    @NotNull @NotBlank
    private String direccion;
    @Column(name = "ESTADO")
    @NotBlank
    private char estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lector")
    @JsonIgnore
    private Set<Prestamo> prestamos = new HashSet<>();
}
