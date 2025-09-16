package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Posteo;

import java.util.List;
import java.util.Optional;

public interface IposteoService {
    List<Posteo> obtenerPosts();
    Optional<Posteo> obtenerPostPorId(Long id);
    void guardarPost(Posteo post);
    void actualizarPost(Long id, Posteo posteoNuevo);
    void borrarPost(Long id);
    void asignarComentarioAPost(Long id_comentario, Long id_post);
}
