package com.example.biblioteca.entity;

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
@Table(name="TBL_ACCESOS")
public class Acceso {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=0L;
    @Column(name = "TITULO")
    @NotNull
    @NotBlank
    private String titulo;
    @Column(name = "ICONO")
    private String icono;
    @Column(name = "URL")
    @NotNull @NotBlank
    private String url;
    @Column(name = "ESTADO")
    @NotBlank
    private char estado;
    @ManyToMany(
            cascade=CascadeType.ALL
    )
    @JoinTable(
            name = "TBL_ACCESOS_ROLES",
            joinColumns = @JoinColumn(name = "ACCESO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
    )
    private Set<Rol> roles = new HashSet<Rol>();
}
