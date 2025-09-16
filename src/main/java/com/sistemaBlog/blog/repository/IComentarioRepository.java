package com.sistemaBlog.blog.repository;

import com.sistemaBlog.blog.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComentarioRepository extends JpaRepository<Comentario, Long> {
}
