package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.DetallePrestamoDTO;
import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.dto.PrestamoRequestDTO;
import com.example.biblioteca.entity.DetallePrestamo;
import com.example.biblioteca.entity.FechaMapper;
import com.example.biblioteca.entity.Prestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FechaMapper.class})
public interface PrestamoMapper {
    PrestamoDTO toDTO(Prestamo entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "'A'")
    @Mapping(target = "fechap", qualifiedByName = "nowAsDate")
    Prestamo toEntity(PrestamoRequestDTO dto);

    List<PrestamoDTO> toDTOList(List<Prestamo> prestamos);

    DetallePrestamoDTO toDTO(DetallePrestamo entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "'A'")
    DetallePrestamo toEntity(DetallePrestamoDTO dto);
}
