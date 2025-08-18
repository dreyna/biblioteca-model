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
@Table(name="TBL_USUARIOS")
public class Usuario {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id=0L;
    @Column(name = "USERNAME", unique = true, length = 20)
    @NotNull
    @NotBlank
    private String username;
    @Column(name = "PASSWORD", length = 120)
    @NotNull @NotBlank
    private String password;
    @Column(name = "ESTADO")
    @NotBlank
    private char estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLEADO_ID", referencedColumnName = "ID", nullable = false, unique = true)
    private Empleado empleado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    @JsonIgnore
    private Set<Prestamo> prestamos;

    @ManyToMany( fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinTable(
            name = "TBL_USUARIOS_ROLES",
            joinColumns = @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROL_ID", referencedColumnName = "ID"), uniqueConstraints =
            {@UniqueConstraint(columnNames = {"USUARIO_ID", "ROL_ID"})})
    private Set<Rol> roles = new HashSet<>();
}
