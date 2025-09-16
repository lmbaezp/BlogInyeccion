package com.sistemaBlog.blog.repository;

import com.sistemaBlog.blog.model.Posteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IposteoRepository extends JpaRepository<Posteo, Long> {
    boolean existsByTitulo(String titulo);
}
