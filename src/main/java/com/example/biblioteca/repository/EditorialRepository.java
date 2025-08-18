package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial,Long> {
}
