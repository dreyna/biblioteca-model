package com.example.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Column(name = "NOMBRE", unique = true,  nullable = false,  length = 50)
    @NotBlank(message = "No debe ser blanco")
    @Size(min = 5, max = 20)
    @Pattern(regexp = "")
    private String nombre;
    @Column(name = "ESTADO", nullable = false, length = 1)
    @NotNull(message = "El estado es obligatorio")
    private Character estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoria")
    @JsonIgnore
    private Set<Seccion> secciones;
}
