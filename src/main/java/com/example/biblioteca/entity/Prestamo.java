package com.example.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="TBL_PRESTAMOS")
public class Prestamo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=0L;

    @Column(name = "FECHA_PRESTAMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechap;

    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.DATE)
    private Date fechae;

    @Column(name = "ESTADO")
    @NotBlank
    private char estado;

    @ManyToOne
    @JoinColumn(name="LECTOR_ID", nullable = false)
    private Lector lector;

    @ManyToOne
    @JoinColumn(name="USUARIO_ID", nullable = false)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestamo")
    @JsonIgnore
    private Set<DetallePrestamo> detalles;
}
