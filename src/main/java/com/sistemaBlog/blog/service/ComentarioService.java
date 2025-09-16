package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Comentario;
import com.sistemaBlog.blog.model.Posteo;
import com.sistemaBlog.blog.repository.IComentarioRepository;
import com.sistemaBlog.blog.repository.IposteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComentarioService implements IComentarioService{

    private IComentarioRepository iComentarioRepository;
    private IposteoRepository iposteoRepository;

    @Autowired
    public ComentarioService(IComentarioRepository iComentarioRepository,
                             IposteoRepository iposteoRepository) {
        this.iComentarioRepository = iComentarioRepository;
        this.iposteoRepository = iposteoRepository;
    }

    @Override
    public List<Comentario> obtenerComentarios() {
        return iComentarioRepository.findAll();
    }

    @Override
    public Optional<Comentario> obtenerComentarioPorId(Long id) {
        return iComentarioRepository.findById(id);
    }

    @Override
    public void guardarComentario(Comentario comentario) {
        if (comentario.getTexto() == null || comentario.getTexto().isBlank()) {
            throw new IllegalArgumentException("El texto es obligatorio");
        }

        if (comentario.getId_post() == null || comentario.getId_post().getId_post() == null) {
            throw new IllegalArgumentException("Debe asignar un post existente al comentario");
        }

        Posteo post = iposteoRepository.findById(comentario.getId_post().getId_post())
                .orElseThrow(() -> new NoSuchElementException(
                        "El post con id " + comentario.getId_post().getId_post() + " no existe"));

        comentario.setId_post(post);

        iComentarioRepository.save(comentario);
    }

    @Override
    public void borrarComentario(Long id) {
        iComentarioRepository.deleteById(id);
    }

    @Override
    public void editarComentario(Long id, Comentario comentarioNuevo) {
        Optional<Comentario> comentarioBD = obtenerComentarioPorId(id);

        if (comentarioBD.isEmpty()) {
            throw new NoSuchElementException("No se encontró el comentario con id " + id);
        }

        Comentario existente = comentarioBD.get();

        if ((comentarioNuevo.getTexto() == null || comentarioNuevo.getTexto().isBlank()) &&
                (comentarioNuevo.getId_post() == null)) {
            throw new IllegalArgumentException("No se modificó ninguna información");
        }
        if (comentarioNuevo.getTexto() != null) {
            existente.setTexto(comentarioNuevo.getTexto());
        }
        if (comentarioNuevo.getId_post() != null) {
            existente.setId_post(comentarioNuevo.getId_post());
        }
        iComentarioRepository.save(existente);
    }
}
