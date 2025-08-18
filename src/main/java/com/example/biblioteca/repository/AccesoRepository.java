package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepository extends JpaRepository<Prestamo,Long> {
}
