package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutorService {
    List<Autor> obtenerAutores();
    Optional<Autor> obtenerAutorPorId(Long id);
    void guardarAutor(Autor autor);
    void eliminarAutor(Long id);
    void editarAutor(Long id, Autor autorNuevo);
}
