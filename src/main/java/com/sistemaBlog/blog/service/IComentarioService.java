package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Comentario;

import java.util.List;
import java.util.Optional;

public interface IComentarioService {
    List<Comentario> obtenerComentarios();
    Optional<Comentario> obtenerComentarioPorId(Long id);
    void guardarComentario(Comentario comentario);
    void borrarComentario(Long id);
    void editarComentario(Long id, Comentario comentarioNuevo);
}
