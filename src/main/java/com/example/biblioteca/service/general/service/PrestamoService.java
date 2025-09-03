package com.example.biblioteca.service.general.service;

import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.dto.PrestamoRequestDTO;
import com.example.biblioteca.entity.Prestamo;
import com.example.biblioteca.service.exception.ServiceException;

import java.util.List;


public interface PrestamoService{
    PrestamoDTO save(PrestamoRequestDTO requestDTO) throws ServiceException;
    List<PrestamoDTO> readAll();

}
