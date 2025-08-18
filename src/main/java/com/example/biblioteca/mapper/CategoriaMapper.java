package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.CategoriaDTO;
import com.example.biblioteca.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO toDto(Categoria categoria);
    Categoria toEntity(CategoriaDTO dto);
    List<CategoriaDTO> toDtoList(List<Categoria> categorias);
}
