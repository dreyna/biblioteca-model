package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.SeccionDTO;
import com.example.biblioteca.entity.Seccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeccionMapper {
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    SeccionDTO toDto(Seccion seccion);

    @Mapping(target = "categoria", ignore = true)
    Seccion toEntity(SeccionDTO dto);
}
