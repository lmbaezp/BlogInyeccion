package com.sistemaBlog.blog.repository;

import com.sistemaBlog.blog.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    boolean existsByNombre(String nombre);
}
