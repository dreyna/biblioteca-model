package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.DetallePrestamoDTO;
import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.dto.PrestamoRequestDTO;
import com.example.biblioteca.entity.DetallePrestamo;
import com.example.biblioteca.entity.Prestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {
    PrestamoDTO toDTO(Prestamo entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "'A'")
    @Mapping(target = "fecha_prestamo", expression = "java(java.time.LocalDateTime.now())")
    Prestamo toEntity(PrestamoRequestDTO dto);

    List<PrestamoDTO> toDTOList(List<Prestamo> prestamos);

    DetallePrestamoDTO toDTO(DetallePrestamo entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "'A'")
    DetallePrestamo toEntity(DetallePrestamoDTO dto);
}
