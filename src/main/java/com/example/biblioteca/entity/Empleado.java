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
@Table(name="TBL_EMPLEADOS")
public class Empleado {
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
    @Column(name = "CORREO")
    @NotNull @NotBlank
    private String correo;
    @Column(name = "ESTADO")
    @NotBlank
    private char estado;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Usuario usuario;
}
