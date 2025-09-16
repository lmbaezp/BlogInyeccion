package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Autor;
import com.sistemaBlog.blog.model.Comentario;
import com.sistemaBlog.blog.model.Posteo;
import com.sistemaBlog.blog.repository.IAutorRepository;
import com.sistemaBlog.blog.repository.IposteoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PosteoService implements IposteoService {

    private IposteoRepository iposteoRepository;
    private IComentarioService iComentarioService;
    private IAutorRepository iAutorRepository;

    @Autowired
    public PosteoService(IposteoRepository iposteorepository, IComentarioService iComentarioService,
                         IAutorRepository iAutorRepository) {
        this.iposteoRepository = iposteorepository;
        this.iComentarioService = iComentarioService;
        this.iAutorRepository = iAutorRepository;
    }

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
        // Validar título
        if (post.getTitulo() == null || post.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (iposteoRepository.existsByTitulo(post.getTitulo())) {
            throw new IllegalArgumentException("Ya existe un post con el título: " + post.getTitulo());
        }

        if (post.getAutor() == null || post.getAutor().getId_autor() == null) {
            throw new IllegalArgumentException("Debe asignar un autor existente al post");
        }

        Autor autor = iAutorRepository.findById(post.getAutor().getId_autor())
                .orElseThrow(() -> new NoSuchElementException("El autor con id " + post.getAutor().getId_autor() + " no existe"));

        post.setAutor(autor);

        iposteoRepository.save(post);
    }

    @Override
    public void actualizarPost(Long id, Posteo posteoNuevo) {
        Optional<Posteo> postBD = obtenerPostPorId(id);

        if (postBD.isEmpty()) {
            throw new NoSuchElementException("No se encontró el post con id " + id);
        }

        Posteo existente = postBD.get();

        if ((posteoNuevo.getTitulo() == null || posteoNuevo.getTitulo().isBlank()) &&
                (posteoNuevo.getAutor() == null)) {
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

    @Override
    @Transactional
    public void asignarComentarioAPost(Long id_comentario, Long id_post) {
        Optional<Posteo> post = obtenerPostPorId(id_post);
        Optional<Comentario> comentario = iComentarioService.obtenerComentarioPorId(id_comentario);
    }

}

