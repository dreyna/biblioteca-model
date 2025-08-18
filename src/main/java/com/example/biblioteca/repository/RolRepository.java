package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
}
