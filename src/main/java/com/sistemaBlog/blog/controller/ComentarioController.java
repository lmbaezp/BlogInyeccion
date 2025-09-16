package com.sistemaBlog.blog.controller;

import com.sistemaBlog.blog.model.Autor;
import com.sistemaBlog.blog.model.Comentario;
import com.sistemaBlog.blog.service.AutorService;
import com.sistemaBlog.blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<?> listaComentarios() {
        List<Comentario> comentarios = comentarioService.obtenerComentarios();
        if (comentarios.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No hay comentarios en la base de datos");
        }
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerComentarioPorId(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.obtenerComentarioPorId(id);
        if (comentario.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el comentario con id: " + id);
        }
        return ResponseEntity.ok(comentario);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarComentario(@RequestBody Comentario comentario) {
        try {
            comentarioService.guardarComentario(comentario);
            return ResponseEntity.ok("Comentario fue agregado con éxito");
        } catch (
                IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (
                Exception e) {
            return ResponseEntity.internalServerError().body("Error inesperado: " + e.getMessage());
        }
    }

    @PatchMapping("/editar/{id}")
    public ResponseEntity<String> actualizarComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        try {
            comentarioService.editarComentario(id, comentario);
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
    public ResponseEntity<String> eliminarComentaro(@PathVariable Long id) {
        Optional<Comentario> comentarioBD = comentarioService.obtenerComentarioPorId(id);

        if (comentarioBD.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el comentario con id: " + id);
        }

        try {
            comentarioService.borrarComentario(id);
            return ResponseEntity.ok("COmentario eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al eliminar el autor: " + e.getMessage());
        }
    }
}
