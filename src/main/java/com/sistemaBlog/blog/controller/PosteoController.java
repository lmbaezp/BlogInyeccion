package com.sistemaBlog.blog.controller;

import com.sistemaBlog.blog.model.Posteo;
import com.sistemaBlog.blog.service.PosteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posteos")
public class PosteoController {
    private PosteoService posteoService;

    @Autowired
    public PosteoController(PosteoService posteoService) {
        this.posteoService = posteoService;
    }

    @GetMapping
    public List<Posteo> listaPosts() {
        return posteoService.obtenerPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPostPorId(@PathVariable Long id) {
        Posteo post = posteoService.obtenerPostPorId(id);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el post con id: " + id);
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarPost(@RequestBody Posteo post) {
        try {
            posteoService.guardarPost(post);
            return ResponseEntity.ok("Persona fue agregada con éxito");
        } catch (
                IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (
                Exception e) {
            return ResponseEntity.internalServerError().body("Error inesperado: " + e.getMessage());
        }
    }
}
