package com.example.biblioteca.repository;

import com.example.biblioteca.entity.DetallePrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo,Long> {
}
