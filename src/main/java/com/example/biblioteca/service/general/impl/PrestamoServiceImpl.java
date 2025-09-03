package com.example.biblioteca.service.general.impl;

import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.dto.PrestamoRequestDTO;
import com.example.biblioteca.entity.Prestamo;
import com.example.biblioteca.mapper.PrestamoMapper;
import com.example.biblioteca.repository.PrestamoRepository;
import com.example.biblioteca.service.general.service.PrestamoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final PrestamoMapper prestamoMapper;

    @Transactional
    @Override
    public PrestamoDTO save(PrestamoRequestDTO requestDTO) throws ServiceException {
        Prestamo prestamo = prestamoMapper.toEntity(requestDTO);
        prestamo.getDetalles().forEach( detalle -> detalle.setPrestamo(prestamo));
        Prestamo prestamo1 = prestamoRepository.save(prestamo);
        return prestamoMapper.toDTO(prestamo1);
    }

    @Override
    public List<PrestamoDTO> readAll() {
        return prestamoMapper.toDTOList(prestamoRepository.findAll());
    }
}
