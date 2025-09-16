package com.sistemaBlog.blog.service;

import com.sistemaBlog.blog.model.Autor;
import com.sistemaBlog.blog.model.Posteo;
import com.sistemaBlog.blog.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutorService implements IAutorService {

    private IAutorRepository iAutorRepository;

    @Autowired
    public AutorService(IAutorRepository iAutorRepository) {
        this.iAutorRepository = iAutorRepository;
    }

    @Override
    public List<Autor> obtenerAutores() {
        return iAutorRepository.findAll();
    }

    @Override
    public Optional<Autor> obtenerAutorPorId(Long id) {
        return iAutorRepository.findById(id);
    }

    @Override
    public void guardarAutor(Autor autor) {
        if (autor.getNombre() == null || autor.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        } else {
            if (iAutorRepository.existsByNombre(autor.getNombre())) {
                throw new IllegalArgumentException("Ya existe un autor con el nombre: " + autor.getNombre());
            }
        }
        if (autor.getEmail() == null || autor.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email del autor es obligatorio");
        }
        iAutorRepository.save(autor);
    }

    @Override
    public void eliminarAutor(Long id) {
        iAutorRepository.deleteById(id);
    }

    @Override
    public void editarAutor(Long id, Autor autorNuevo) {
        Optional<Autor> autorBD = obtenerAutorPorId(id);

        if (autorBD.isEmpty()) {
            throw new NoSuchElementException("No se encontró el autor con id " + id);
        }

        Autor existente = autorBD.get();

        if ((autorNuevo.getNombre() == null || autorNuevo.getNombre().isBlank()) &&
                (autorNuevo.getEmail() == null || autorNuevo.getEmail().isBlank())) {
            throw new IllegalArgumentException("No se modificó ninguna información");
        }
        if (autorNuevo.getNombre() != null) {
            existente.setNombre(autorNuevo.getNombre());
        }
        if (autorNuevo.getEmail() != null) {
            existente.setEmail(autorNuevo.getEmail());
        }
        iAutorRepository.save(existente);
    }
}
