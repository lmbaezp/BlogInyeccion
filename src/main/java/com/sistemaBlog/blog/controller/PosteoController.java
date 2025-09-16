package com.sistemaBlog.blog.controller;

import com.sistemaBlog.blog.model.Posteo;
import com.sistemaBlog.blog.service.PosteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/posteos")
public class PosteoController {
    private PosteoService posteoService;

    @Autowired
    public PosteoController(PosteoService posteoService) {
        this.posteoService = posteoService;
    }

    @GetMapping
    public ResponseEntity<?> listaPosts() {
        List<Posteo> posts = posteoService.obtenerPosts();
        if (posts.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No hay posteos en la base de datos");
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPostPorId(@PathVariable Long id) {
        Optional<Posteo> post = posteoService.obtenerPostPorId(id);
        if (post.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el post con id: " + id);
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarPost(@RequestBody Posteo post) {
        try {
            posteoService.guardarPost(post);
            return ResponseEntity.ok("Post fue agregado con éxito");
        } catch (
                IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (
                Exception e) {
            return ResponseEntity.internalServerError().body("Error inesperado: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarPost(@PathVariable Long id, @RequestBody Posteo postNuevo) {
        try {
            posteoService.actualizarPost(id, postNuevo);
            return ResponseEntity.ok("Post editado con éxito");
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error inesperado: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> actualizarParcial(@PathVariable Long id, @RequestBody Posteo post) {
        try {
            posteoService.actualizarPost(id, post);
            return ResponseEntity.ok("Post actualizado con éxito");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Advertencia: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error inesperado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPost(@PathVariable Long id) {
        Optional<Posteo> postBD = posteoService.obtenerPostPorId(id);

        if (postBD.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el post con id: " + id);
        }

        try {
            posteoService.borrarPost(id);
            return ResponseEntity.ok("Post eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al eliminar el post: " + e.getMessage());
        }
    }

}
