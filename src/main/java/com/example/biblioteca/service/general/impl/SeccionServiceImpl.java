package com.example.biblioteca.service.general.impl;

import com.example.biblioteca.controller.error.ResourceNotFoundException;
import com.example.biblioteca.dto.SeccionDTO;
import com.example.biblioteca.entity.Categoria;
import com.example.biblioteca.entity.Seccion;
import com.example.biblioteca.mapper.SeccionMapper;
import com.example.biblioteca.repository.CategoriaRepository;
import com.example.biblioteca.repository.SeccionRepository;
import com.example.biblioteca.service.general.service.SeccionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class SeccionServiceImpl implements SeccionService {
    private final SeccionRepository seccionRepository;
    private final CategoriaRepository categoriaRepository;
    private final SeccionMapper seccionMapper;
    @Override
    public List<SeccionDTO> findAll() throws ServiceException {
        List<Seccion> list = seccionRepository.findAll();
        return list.stream().map(l->seccionMapper.toDto(l)).collect(Collectors.toList());
    }

    @Override
    public Optional<SeccionDTO> findById(long id) throws ServiceException {
        return seccionRepository.findById(id).map(l->seccionMapper.toDto(l));
    }

    @Override
    public List<SeccionDTO> findByObject(SeccionDTO seccionDTO) throws ServiceException {
        return List.of();
    }

    @Override
    public SeccionDTO save(SeccionDTO seccionDTO) throws ServiceException {
        Categoria categoria = categoriaRepository.findById(seccionDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe categoría con id " + seccionDTO.getCategoriaId()));
        Seccion seccion = seccionMapper.toEntity(seccionDTO);
        seccion.setCategoria(categoria);
        return seccionMapper.toDto(seccionRepository.save(seccion));
    }

    @Override
    @Transactional
    public SeccionDTO update(SeccionDTO seccionDTO) throws ServiceException {
        Optional<Seccion> seccion1 = seccionRepository.findById(seccionDTO.getId());
        Seccion seccion = seccionRepository.findById(seccionDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sección no encontrada"));
        Categoria categoria = categoriaRepository.findById(seccionDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        seccion.setNombre(seccionDTO.getNombre());
        seccion.setEstado(seccionDTO.getEstado().charAt(0));
        seccion.setCategoria(categoria);
        Seccion actualizada = seccionRepository.save(seccion);
        log.info("action=update entity=Seccion id={} outcome=OK", actualizada.getId());
        return seccionMapper.toDto(actualizada);
    }

    @Override
    public void deleteLogic(Long id) throws ServiceException {
        try {
            Seccion seccion = seccionRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("La categoría con id " + id + " no existe"));
            seccion.setEstado('I');
            seccionRepository.save(seccion);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al intentar eliminar la categoría con id " + id, e);
        }
    }
}
