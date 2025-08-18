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
@Table(name="TBL_ROLES")
public class Rol {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id=0L;
    @Column(name = "NOMBRE")
    @NotNull
    @NotBlank
    private String nombre;
    @Column(name = "ESTADO")
    @NotBlank
    private char estado;
}
