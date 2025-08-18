package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion,Long> {
}
