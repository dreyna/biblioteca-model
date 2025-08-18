package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.CategoriaDTO;
import com.example.biblioteca.dto.SeccionDTO;
import com.example.biblioteca.entity.Categoria;
import com.example.biblioteca.entity.Seccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeccionMapper {
    @Mapping(source = "categoria.id", target = "categoriaId")
    SeccionDTO toDto(Seccion seccion);

    @Mapping(target = "categoria", ignore = true)
    Seccion toEntity(SeccionDTO dto);

    List<SeccionDTO> toDtoList(List<Seccion> secciones);
}
