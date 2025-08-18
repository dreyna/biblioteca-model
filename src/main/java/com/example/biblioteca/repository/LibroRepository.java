package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Lector,Long> {
}
