package com.sistemaBlog.blog.controller;

import com.sistemaBlog.blog.model.Autor;
import com.sistemaBlog.blog.model.Posteo;
import com.sistemaBlog.blog.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<?> listaAutores() {
        List<Autor> autores = autorService.obtenerAutores();
        if (autores.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No hay autores en la base de datos");
        }
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAutorPorId(@PathVariable Long id) {
        Optional<Autor> autor = autorService.obtenerAutorPorId(id);
        if (autor.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el autor con id: " + id);
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarAutor(@RequestBody Autor autor) {
        try {
            autorService.guardarAutor(autor);
            return ResponseEntity.ok("Autor fue agregado con éxito");
        } catch (
                IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (
                Exception e) {
            return ResponseEntity.internalServerError().body("Error inesperado: " + e.getMessage());
        }
    }

    @PatchMapping("/editar/{id}")
    public ResponseEntity<String> actualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        try {
            autorService.editarAutor(id, autor);
            return ResponseEntity.ok("Autor actualizado con éxito");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Advertencia: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error inesperado: " + e.getMessage());
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Long id) {
        Optional<Autor> autorBD = autorService.obtenerAutorPorId(id);

        if (autorBD.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el autor con id: " + id);
        }

        try {
            autorService.eliminarAutor(id);
            return ResponseEntity.ok("Autor eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al eliminar el autor: " + e.getMessage());
        }
    }

}

