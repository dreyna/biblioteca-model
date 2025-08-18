package com.example.biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="TBL_DETALLE_PRESTAMOS")
public class DetallePrestamo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id=0L;
    @Column(name = "OBSERVACIONES")
    @NotNull
    @NotBlank
    private String observaciones;
    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechae;
    @Column(name = "ESTADO")
    @NotBlank
    private char estado;

    @ManyToOne
    @JoinColumn(name="LIBRO_ID", nullable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name="PRESTAMO_ID", nullable = false)
    private Prestamo prestamo;

}
