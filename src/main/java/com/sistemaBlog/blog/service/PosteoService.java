package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Posteo;
import com.sistemaBlog.blog.repository.IposteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PosteoService implements IposteoService {

    private IposteoRepository iposteoRepository;

    @Autowired
    public PosteoService(IposteoRepository iposteorepository) {
        this.iposteoRepository = iposteorepository;
    }

    ;

    @Override
    public List<Posteo> obtenerPosts() {
        return iposteoRepository.findAll();
    }

    @Override
    public Optional<Posteo> obtenerPostPorId(Long id) {
        return iposteoRepository.findById(id);
    }

    @Override
    public void guardarPost(Posteo post) {
        if (post.getTitulo() == null || post.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El titulo es obligatorio");
        } else {
            if (iposteoRepository.existsByTitulo(post.getTitulo())) {
                throw new IllegalArgumentException("Ya existe un post con el nombre: " + post.getTitulo());
            }
        }
        if (post.getAutor() == null) {
            throw new IllegalArgumentException("El autor es obligatorio");
        }
        iposteoRepository.save(post);
    }

    @Override
    public void actualizarPost(Long id, Posteo posteoNuevo) {
        Optional<Posteo> postBD = obtenerPostPorId(id);
        if (postBD.isEmpty()) {
            throw new NoSuchElementException("No se encontró el post con id " + id);
        }

        Posteo existente = postBD.get();

        if (posteoNuevo.getTitulo() == null || posteoNuevo.getTitulo().isBlank() ||
                posteoNuevo.getAutor() == null || posteoNuevo.getAutor().isBlank()) {
            throw new IllegalArgumentException("No pueden haber campos vacíos");
        }
        existente.setTitulo(posteoNuevo.getTitulo());
        existente.setAutor(posteoNuevo.getAutor());

        iposteoRepository.save(existente);

    }

    @Override
    public void actualizarPartesPost(Long id, Posteo posteoNuevo) {
        Optional<Posteo> postBD = obtenerPostPorId(id);

        if (postBD.isEmpty()) {
            throw new NoSuchElementException("No se encontró el post con id " + id);
        }

        Posteo existente = postBD.get();

        if ((posteoNuevo.getTitulo() == null || posteoNuevo.getTitulo().isBlank()) &&
                (posteoNuevo.getAutor() == null || posteoNuevo.getAutor().isBlank())) {
            throw new IllegalArgumentException("No se modificó ninguna información");
        }
        if (posteoNuevo.getTitulo() != null) {
            existente.setTitulo(posteoNuevo.getTitulo());
        }
        if (posteoNuevo.getAutor() != null) {
            existente.setAutor(posteoNuevo.getAutor());
        }
        iposteoRepository.save(existente);
    }

    @Override
    public void borrarPost(Long id) {
        iposteoRepository.deleteById(id);
    }
}
